package com.rrtv.rpc.core.serialization;

/**
 * @Author: changjiu.wang
 * @Date: 2021/7/24 22:38
 */
public class SerializationFactory {

    public static RpcSerialization getRpcSerialization(SerializationTypeEnum typeEnum) {
        switch (typeEnum) {
            case HESSIAN:
                return new HessianSerialization();
            case JSON:
                return new JsonSerialization();
            default:
                throw new IllegalArgumentException("serialization type is illegal");
        }
    }

}
