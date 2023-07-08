package com.rrtv.rpc.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname RpcClientProperties
 * @Description
 * @Date 2021/7/7 14:40
 * @Created by wangchangjiu
 */
@Data
@ConfigurationProperties(prefix = "rpc.server")
public class RpcServerProperties {

    /**
     *  服务启动端口
     */
    private Integer port = 8090;

    /**
     *  服务名称
     */
    private String appName;

    /**
     *  注册中心地址
     */
    private String registryAddr = "127.0.0.1:2181";

}
