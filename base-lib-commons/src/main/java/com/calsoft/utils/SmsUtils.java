package com.calsoft.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.calsoft.exception.SmsFailureException;
import com.calsoft.notification.vo.NotificationVO;
import com.calsoft.pos.kafka.producer.CalsoftEventProducer;
import com.calsoft.pos.model.CoreConfigData;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.repository.ConfigRepository;

@Component
public class SmsUtils {

	@Autowired
	CalsoftEventProducer calsoftEventProducer;

	@Autowired
	private SmsUtils smsUtils;

	public void messageTemplateForSmsc(CoreConfigData coreConfigData, String message, String userName)
			throws SmsFailureException {

		String url = coreConfigData.getSmsUrl() + coreConfigData.getSmscApiKey() + "&clientId="
				+ coreConfigData.getSmscClientId() + "&msisdn=" + userName.trim() + "&sid="
				+ coreConfigData.getSmscSenderId() + "&msg=" + message + "&fl=0&gwid=2";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		if (result.getStatusCode() == HttpStatus.OK) {

			JSONObject myObject = new JSONObject(result.getBody());

			JSONObject jsonObject = (JSONObject) myObject;

			String errorCode = String.valueOf(jsonObject.get("ErrorCode"));

			if (!errorCode.equalsIgnoreCase("000")) {
				throw new SmsFailureException(errorCode);
			}

		} else if (result.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new SmsFailureException("");
		}
	}

	public void messageTemplateForFastSms(CoreConfigData coreConfigData, String variable, String userName,
			String messgaeTemplate) throws SmsFailureException {
		String url = coreConfigData.getSmsUrl();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		headers.add("authorization", coreConfigData.getSmscApiKey());
		Map<String, Object> requestBodyJson = new HashMap<String, Object>();
		requestBodyJson.put("route", "dlt");
		requestBodyJson.put("sender_id", coreConfigData.getSmscSenderId());
		requestBodyJson.put("message", messgaeTemplate);
		requestBodyJson.put("variables_values", variable);
		requestBodyJson.put("numbers", userName);

		HttpEntity<String> entity = new HttpEntity<>(RestUtils.toJSON(requestBodyJson), headers);
		ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class);

		if (result.getStatusCode() == HttpStatus.OK) {

			JSONObject myObject = new JSONObject(result.getBody());

			JSONObject jsonObject = (JSONObject) myObject;

			Boolean errorCode = Boolean.parseBoolean((jsonObject.get("return")).toString());

			if (!errorCode) {
				throw new SmsFailureException("");
			}

		} else {

			throw new SmsFailureException("");

		}
	}

	public void messageTemplateForSmsc(NotificationVO notificationVO, String message) throws SmsFailureException {
		String url = notificationVO.getSmsUrl() + notificationVO.getSmscApiKey() + "&clientId="
				+ notificationVO.getSmscClientId() + "&msisdn=" + notificationVO.getNumber().trim() + "&sid="
				+ notificationVO.getSmscSenderId() + "&msg=" + message + "&fl=0&gwid=2";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		if (result.getStatusCode() == HttpStatus.OK) {

			JSONObject myObject = new JSONObject(result.getBody());

			JSONObject jsonObject = (JSONObject) myObject;

			String errorCode = String.valueOf(jsonObject.get("ErrorCode"));

			if (!errorCode.equalsIgnoreCase("000")) {
				throw new SmsFailureException(errorCode);
			}

		} else if (result.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new SmsFailureException("");
		}

	}

	public void messageTemplateForFastSms(NotificationVO notificationVO) throws SmsFailureException {
		String url = notificationVO.getSmsUrl();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		headers.add("authorization", notificationVO.getSmscApiKey());
		Map<String, Object> requestBodyJson = new HashMap<String, Object>();
		requestBodyJson.put("route", "dlt");
		requestBodyJson.put("sender_id", notificationVO.getSmscSenderId());
		requestBodyJson.put("message", notificationVO.getMessage());
		requestBodyJson.put("variables_values", notificationVO.getSmsValue());
		requestBodyJson.put("numbers", notificationVO.getNumber());

		HttpEntity<String> entity = new HttpEntity<>(RestUtils.toJSON(requestBodyJson), headers);
		ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class);

		if (result.getStatusCode() == HttpStatus.OK) {

			JSONObject myObject = new JSONObject(result.getBody());

			JSONObject jsonObject = (JSONObject) myObject;

			Boolean errorCode = Boolean.parseBoolean((jsonObject.get("return")).toString());

			if (!errorCode) {
				throw new SmsFailureException("");
			}

		} else {

			throw new SmsFailureException("");

		}
	}

	public void triggerSms(NotificationVO notificationVO, String key, String tenantId) {
		try {
			calsoftEventProducer.sendEvent("sms-events", key, notificationVO, tenantId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
