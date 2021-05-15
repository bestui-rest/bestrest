package com.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.consume.Consume;

@Controller
@RequestMapping("/user")
public class IndexController {
@Autowired
private Consume consume;
@ResponseBody
@RequestMapping("/user")
public String toIndex(){
	return consume.doSayHello("5555555555");
}

}

