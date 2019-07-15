package com.summary.controller;

import com.summary.aop.annotation.Logs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(path = "/hi")
    @Logs  // 实现自定义注解 进行访问日志输出
    public String hi(String name,String pass){
        return name+pass;
    }
}
