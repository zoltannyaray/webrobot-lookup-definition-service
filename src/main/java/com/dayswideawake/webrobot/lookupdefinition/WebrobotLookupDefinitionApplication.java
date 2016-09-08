package com.dayswideawake.webrobot.lookupdefinition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebrobotLookupDefinitionApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebrobotLookupDefinitionApplication.class, args);
	}
}
