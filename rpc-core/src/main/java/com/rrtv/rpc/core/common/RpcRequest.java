package com.rrtv.rpc.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: changjiu.wang
 * @Date: 2021/7/24 22:55
 */
@Data
public class RpcRequest implements Serializable {

    /**
     * 请求的服务名 + 版本
     */
    private String serviceName;
    /**
     * 请求调用的方法
     */
    private String method;

    /**
     *  参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     *  参数
     */
    private Object[] parameters;

}
