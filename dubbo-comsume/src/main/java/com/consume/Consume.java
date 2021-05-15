package com.consume;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zyd.dubbo.inter.ProService;

@Component("consume")
public class Consume {

    @Reference
    private ProService annotationService;
    
    public String doSayHello(String name) {
        return annotationService.sayHell(name);
    }
}