package com.example.HealFitNest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@EnableWebSecurity
public class HealFitNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealFitNestApplication.class, args);
	}

}
