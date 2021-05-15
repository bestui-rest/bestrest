package com.consume.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiImpl {
    @Autowired 
    private HelloApi helloApi;
    
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(String name){
        return helloApi.sayHello(name);
    }
}
