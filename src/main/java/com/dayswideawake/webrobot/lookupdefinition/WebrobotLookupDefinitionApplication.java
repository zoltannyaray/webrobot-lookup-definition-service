package com.dayswideawake.webrobot.lookupdefinition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.dayswideawake.webrobot.lookupdefinition.messaging.Channels;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(value=Channels.class)
public class WebrobotLookupDefinitionApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebrobotLookupDefinitionApplication.class, args);
	}
}
