package com.taha.webfluxrealtimechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WebfluxRealtimeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxRealtimeChatApplication.class, args);
	}

}
