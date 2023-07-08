package com.rrtv.rpc.core.register;

import com.rrtv.rpc.core.common.ServiceInfo;

import java.io.IOException;

/**
 * @Classname RegistryService
 * @Description 服务注册发现
 * @Date 2021/7/23 14:29
 * @Created by wangchangjiu
 */
public interface RegistryService {

    void register(ServiceInfo serviceInfo) throws Exception;

    void unRegister(ServiceInfo serviceInfo) throws Exception;

    void destroy() throws IOException;

}
