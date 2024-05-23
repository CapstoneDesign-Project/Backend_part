package com.example.una;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.example.una.schoolSchedule.repository"})
public class UnaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnaProjectApplication.class, args);
	}

}
