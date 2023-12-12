package com.microserice.projectEuroka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProjectEurokaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEurokaApplication.class, args);
	}

}
