package com.rrtv.rpc.core.discovery;

import com.rrtv.rpc.core.balancer.LoadBalance;
import com.rrtv.rpc.core.common.ServiceInfo;
import com.rrtv.rpc.core.common.ServiceUtil;
import com.rrtv.rpc.core.register.RegistryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Classname ZookeeperRegistryService
 * @Description
 * @Date 2021/7/23 14:35
 * @Created by wangchangjiu
 */
@Slf4j
public class ZookeeperDiscoveryService implements DiscoveryService {

    public static final int BASE_SLEEP_TIME_MS = 1000;
    public static final int MAX_RETRIES = 3;
    public static final String ZK_BASE_PATH = "/demo_rpc";

    private ServiceDiscovery<ServiceInfo> serviceDiscovery;

    private LoadBalance loadBalance;

    public ZookeeperDiscoveryService(String registryAddr, LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
        try {
            CuratorFramework client = CuratorFrameworkFactory.newClient(registryAddr, new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS, MAX_RETRIES));
            client.start();
            JsonInstanceSerializer<ServiceInfo> serializer = new JsonInstanceSerializer<>(ServiceInfo.class);
            this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
                    .client(client)
                    .serializer(serializer)
                    .basePath(ZK_BASE_PATH)
                    .build();
            this.serviceDiscovery.start();
        } catch (Exception e) {
            log.error("serviceDiscovery start error :{}", e);
        }
    }


    /**
     *  服务发现
     * @param serviceName
     * @return
     * @throws Exception
     */
    @Override
    public ServiceInfo discovery(String serviceName) throws Exception {
        Collection<ServiceInstance<ServiceInfo>> serviceInstances = serviceDiscovery.queryForInstances(serviceName);
        return CollectionUtils.isEmpty(serviceInstances) ? null
                : loadBalance.chooseOne(serviceInstances.stream().map(ServiceInstance::getPayload).collect(Collectors.toList()));
    }

}
