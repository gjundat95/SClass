package com.jundat95;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class GpsGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpsGroupApplication.class, args);

	}

}
