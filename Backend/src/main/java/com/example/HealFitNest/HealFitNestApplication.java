package com.example.HealFitNest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class HealFitNestApplication {

//	@Autowired
//	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(HealFitNestApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer  corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};

	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendEmail(){
//		emailSenderService.sendEmail("ish.asthana@gmail.com","Demo","Hello isha");
//	}
}
