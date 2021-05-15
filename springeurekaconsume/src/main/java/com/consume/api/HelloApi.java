package com.consume.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//服务提供者 apppication.name
@Component
@FeignClient(value = "provider",name = "provider")
public interface HelloApi {
	   @RequestMapping(method = RequestMethod.GET, value = "/hello")
	    public String sayHello(@RequestParam("name")String name);
}
