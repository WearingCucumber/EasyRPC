package com.rrtv.rpc.server.config;

import com.rrtv.rpc.core.register.RegistryService;
import com.rrtv.rpc.core.register.ZookeeperRegistryService;
import com.rrtv.rpc.server.RpcServerProvider;
import com.rrtv.rpc.server.transport.NettyRpcServer;
import com.rrtv.rpc.server.transport.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RpcServerConfiguration
 * @Description rpc server 配置
 * @Date 2021/7/5 11:34
 * @Created by wangchangjiu
 */
@Configuration
@EnableConfigurationProperties(RpcServerProperties.class)
public class RpcServerAutoConfiguration {

    @Autowired
    private RpcServerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public RegistryService registryService() {
        return new ZookeeperRegistryService(properties.getRegistryAddr());
    }

    @Bean
    @ConditionalOnMissingBean(RpcServer.class)
    public RpcServer RpcServer() {
        return new NettyRpcServer();
    }

    @Bean
    @ConditionalOnMissingBean(RpcServerProvider.class)
    public RpcServerProvider rpcServerProvider(@Autowired RegistryService registryService,
                                        @Autowired RpcServer rpcServer,
                                        @Autowired RpcServerProperties rpcServerProperties){
        return new RpcServerProvider(registryService, rpcServer, rpcServerProperties);
    }
}
