package com.cocktaildb.drinksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DrinksserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrinksserviceApplication.class, args);
	}


	@Bean
	RestTemplate restTemplate(){

		return  new RestTemplate();
	}

}
