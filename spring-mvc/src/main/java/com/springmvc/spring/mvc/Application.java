package com.springmvc.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com/springmvc/spring/mvc/models"}) //scan jpa entity
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
