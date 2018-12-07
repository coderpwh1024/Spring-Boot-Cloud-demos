package com.why.MyProject;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WHYApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WHYApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
