package com.depart.depart6;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Depart6Application {

	public static void main(String[] args) {
		SpringApplication.run(Depart6Application.class, args);
	}



	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
