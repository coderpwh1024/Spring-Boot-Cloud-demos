package com.github.lly835;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.github.lly835.mapper")
public class PayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayDemoApplication.class, args);
	}
}
