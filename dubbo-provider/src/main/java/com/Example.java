package com;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;



@SpringBootApplication
@EnableDubbo
public class Example {

	public static void main(String[] args) {
		SpringApplication.run(Example.class, args);
	}

}