package com.rrtv.rpc.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: changjiu.wang
 * @Date: 2021/7/24 22:56
 */
@Data
public class RpcResponse implements Serializable {

    private Object data;
    private String message;

}
