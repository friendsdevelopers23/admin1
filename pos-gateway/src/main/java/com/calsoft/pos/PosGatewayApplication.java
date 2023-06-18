package com.calsoft.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableCaching
public class PosGatewayApplication {

	public static ApplicationContext ctx;

	//public static final String PROPERTY_FILE_LOC = "file:/opt/microservice/config/application.yml";
	public static final String PROPERTY_FILE_LOC ="file:C:\\appconfig\\application.yml";

	public static void main(String args[]) {
		System.setProperty("spring.config.location", PROPERTY_FILE_LOC);
		ctx = SpringApplication.run(PosGatewayApplication.class, args);

	}

}
