package com.heroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value= {"classpath:messages.properties"})
public class HeroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesApplication.class, args);
	}

}
