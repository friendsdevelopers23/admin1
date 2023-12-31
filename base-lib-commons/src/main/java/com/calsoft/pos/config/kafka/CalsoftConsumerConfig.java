package com.calsoft.pos.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableKafka
@Slf4j
public class CalsoftConsumerConfig {

	@Bean
	ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
			ConsumerFactory<Object, Object> kafkaConsumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();

		configurer.configure(factory, kafkaConsumerFactory);
		// factory.setConcurrency(1););

		// custom Error Handler
		factory.setErrorHandler(((thrownException, data) -> {
			log.info("Exception in consumerConfig is {} and the record is {}", thrownException.getMessage(), data);
			// persist
		}));

		// retry
		factory.setRetryTemplate(retryTemplate());

		// recovery

		factory.setRecoveryCallback((context -> {
			if (context.getLastThrowable().getCause() instanceof RecoverableDataAccessException) {
				// invoke recovery logic
				log.info("Inside the recoverable logic");

			} else {
				log.info("Inside the non recoverable logic");
				throw new RuntimeException(context.getLastThrowable().getMessage());
			}

			return null;
		}));

		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

		return factory;
	}

	private RetryTemplate retryTemplate() {

		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(1000);
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(simpleRetryPolicy());
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		return retryTemplate;
	}

	private RetryPolicy simpleRetryPolicy() {
		Map<Class<? extends Throwable>, Boolean> exceptionsMap = new HashMap<>();
		exceptionsMap.put(IllegalArgumentException.class, false);
		exceptionsMap.put(RecoverableDataAccessException.class, true);
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(3, exceptionsMap, true);
		return simpleRetryPolicy;

	}
}
