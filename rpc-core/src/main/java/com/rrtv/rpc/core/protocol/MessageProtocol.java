package com.rrtv.rpc.core.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname MessageProtocol
 * @Description 消息协议
 * @Date 2021/7/23 15:33
 * @Created by wangchangjiu
 */
@Data
public class MessageProtocol<T> implements Serializable {

    /**
     *  消息头
     */
    private MessageHeader header;

    /**
     *  消息体
     */
    private T body;

}
