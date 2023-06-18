
package com.calsoft.pos.kafka.producer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.calsoft.utils.UserManagementConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CalsoftEventProducer {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	ObjectMapper objectMapper;

	public ListenableFuture<SendResult<String, String>> sendEvent(String topic, String key, Object values,
			String tenantId) throws JsonProcessingException {

		String value = objectMapper.writeValueAsString(values);

		ProducerRecord<String, String> producerRecord = buildProducerRecord(key, value, topic, tenantId);

		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(producerRecord);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				handleFailure(key, value, ex);
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				handleSuccess(key, value, result);
			}
		});

		return listenableFuture;

	}

	public ListenableFuture<SendResult<String, String>> sendEventWithAccessToken(String topic, String key,
			Object values, String tenantId, Map<String, String> accessToken) throws JsonProcessingException {
		String value = objectMapper.writeValueAsString(values);

		ProducerRecord<String, String> producerRecord = buildProducerRecordWithAccessToken(key, value, topic, tenantId,
				accessToken);

		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(producerRecord);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				handleFailure(key, value, ex);
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				handleSuccess(key, value, result);
			}
		});

		return listenableFuture;

	}

	private ProducerRecord<String, String> buildProducerRecord(String key, String value, String topic,
			String tenantId) {

		RecordHeader recordHeaders1 = new RecordHeader("event-source", "scanner".getBytes());

		RecordHeader recordHeaders2 = new RecordHeader("tenant", tenantId.getBytes());

		List<Header> recordHeaders = Arrays.asList(recordHeaders1, recordHeaders2);

		return new ProducerRecord<>(topic, null, key, value, recordHeaders);
	}

	private ProducerRecord<String, String> buildProducerRecordWithAccessToken(String key, String value, String topic,
			String tenantId, Map<String, String> accessToken) {
		RecordHeader recordHeaders1 = new RecordHeader("event-source", "scanner".getBytes());

		RecordHeader recordHeaders2 = new RecordHeader("tenant", tenantId.getBytes());

		RecordHeader recordHeaders3 = new RecordHeader(UserManagementConstant.XSRF_TOKEN, accessToken.get(UserManagementConstant.XSRF_TOKEN).getBytes());
		
		RecordHeader recordHeaders4 = new RecordHeader(UserManagementConstant.X_XSRF_TOKEN, accessToken.get(UserManagementConstant.X_XSRF_TOKEN).getBytes());

		List<Header> recordHeaders = Arrays.asList(recordHeaders1, recordHeaders2, recordHeaders3, recordHeaders4);

		return new ProducerRecord<>(topic, null, key, value, recordHeaders);
	}

	public SendResult<String, String> sendLibraryEventSynchronous(String topic, String key, Object values)
			throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
		String value = objectMapper.writeValueAsString(values);
		SendResult<String, String> sendResult = null;
		try {
			sendResult = kafkaTemplate.sendDefault(key, value).get(1, TimeUnit.SECONDS);
		} catch (ExecutionException | InterruptedException e) {
			log.error("ExecutionException/InterruptedException Sending the Message and the exception is {}",
					e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception Sending the Message and the exception is {}", e.getMessage());
			throw e;
		}
		return sendResult;
	}

	private void handleFailure(String key, String value, Throwable ex) {
		log.error("Error Sending the Message and the exception is {}", ex.getMessage());
		try {
			throw ex;
		} catch (Throwable throwable) {
			log.error("Error in OnFailure: {}", throwable.getMessage());
		}

	}

	private void handleSuccess(String key, String value, SendResult<String, String> result) {
		log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value,
				result.getRecordMetadata().partition());
	}

}
