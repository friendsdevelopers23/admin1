
package com.calsoft.pos.usermanagement.apidiscover;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {

	@Bean
	public NewTopic MailEvents() {
		return TopicBuilder.name("mail-events").partitions(1).replicas(1).build();
	}
	
	@Bean
	public NewTopic IndexEvents() {
		return TopicBuilder.name("index-events").partitions(1).replicas(1).build();
	}
	
	@Bean
	public NewTopic InventoryEvents() {
		return TopicBuilder.name("inventory-events").partitions(1).replicas(1).build();
	}
	
	@Bean
	public NewTopic SmsEvents() {
		return TopicBuilder.name("sms-events").partitions(1).replicas(1).build();
	}

}
