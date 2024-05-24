package com.Projects.app.Authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetServicesApplication.class, args);

	}

}
