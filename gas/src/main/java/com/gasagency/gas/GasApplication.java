package com.gasagency.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GasApplication.class, args);
	}
	
	@Bean
	public static RestTemplate get()
	{
		return new RestTemplate();
	}

}
