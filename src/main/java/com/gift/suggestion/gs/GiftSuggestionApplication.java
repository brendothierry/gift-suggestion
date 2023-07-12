package com.gift.suggestion.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan({"com.gift.suggestion.gs"})
@Configuration
public class GiftSuggestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftSuggestionApplication.class, args);
	}

}
