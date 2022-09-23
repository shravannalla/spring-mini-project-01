package com.shravan.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@EnableAutoConfiguration(exclude= ErrorMvcAutoConfiguration.class)
@SpringBootApplication
public class SpringBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBookStoreApplication.class, args);
	}

}
