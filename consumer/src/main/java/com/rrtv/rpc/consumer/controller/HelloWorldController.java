package com.rrtv.rpc.consumer.controller;

import com.rrtv.rpc.api.service.HelloWordService;
import com.rrtv.rpc.client.annotation.RpcAutowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname HelloWordController
 * @Description
 * @Date 2021/7/5 16:25
 * @Created by wangchangjiu
 */
@Controller
public class HelloWorldController {

    @RpcAutowired(version = "1.0")
    private HelloWordService helloWordService;

    @GetMapping("/hello/world")
    public ResponseEntity<String> pullServiceInfo(@RequestParam("name") String name){
        return  ResponseEntity.ok(helloWordService.sayHello(name));
    }


}
