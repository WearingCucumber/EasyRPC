package com.rrtv.rpc.core.balancer;

import com.rrtv.rpc.core.common.ServiceInfo;

import java.util.Collection;
import java.util.List;

/**
 * 负载均衡算法接口
 */
public interface LoadBalance {
    /**
     *
     * @param services
     * @return
     */
    ServiceInfo chooseOne(List<ServiceInfo> services);
}
