package com.calsoft.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.calsoft.pos.config.BaseLanucher;
import com.calsoft.utils.CoreProperties;

@Configuration
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@ComponentScan({ "com.calsoft" })
@EntityScan(basePackages = { "com.calsoft" })
@EnableJpaRepositories(basePackages = { "com.calsoft" })
@EnableConfigurationProperties(CoreProperties.class)
@EnableCaching
@EnableScheduling

public class BillingLauncher {

	public static ApplicationContext ctx;

	public static void main(String args[]) {
		System.setProperty("spring.config.location", BaseLanucher.PROPERTY_FILE_LOC);
		ctx = SpringApplication.run(BillingLauncher.class, args);

	}

}
