package com.zyd;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
@SpringBootApplication
@MapperScan("com.zyd.dao")
public class Example {
	public static void main(String[] args) {
		SpringApplication.run(Example.class, args);
	}

}