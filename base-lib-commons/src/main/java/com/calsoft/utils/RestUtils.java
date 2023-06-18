package com.calsoft.utils;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.calsoft.model.ApiResponse;
import com.calsoft.model.AuthenticationRequest;

@Component
public class RestUtils {

	@Autowired
	UserUtils userUtils;

	public static final String CUSTOMER_BYMAIL_API = "/api/customer/authorize/username/";
	public static final String CORE_AUTH_ACCESS_TOKEN = "/oauth/token";
	public static final String UPDATE_PRODUCT_INDEX_API = "/api/manager/product/db/";
	public static final int EXPIRY_TIME = 30;
	
	public static final int OTP_EXPIRY_TIME = 10;
	public static final String NEW_CUSTOMER_SAVE_API = "/api/ig/startupuser";
	static Logger logger = LoggerFactory.getLogger(RestUtils.class);

	public static ResponseEntity<ApiResponse> post(String uri, AuthenticationRequest authenticationRequest,
			String requestJson, String tenantId) {
		return getApiResponseResponseEntity(uri, authenticationRequest, RequestMethod.POST, requestJson, tenantId);
	}

	public static ResponseEntity<ApiResponse> get(String uri, AuthenticationRequest authenticationRequest,
			String requestJson, String tenantId) {
		return getApiResponseResponseEntity(uri, authenticationRequest, RequestMethod.GET, requestJson, tenantId);
	}

	private static ResponseEntity<ApiResponse> getApiResponseResponseEntity(String uri,
			AuthenticationRequest authenticationRequest, RequestMethod requestMethod, String requestJson,
			String tenantId) {
		ResponseEntity<String> responseEntity = null;
		try {

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			if (authenticationRequest.getAccessToken() != null) {
				headers.add(UserManagementConstant.XSRF_TOKEN, authenticationRequest.getAccessToken());
				headers.add(UserManagementConstant.X_XSRF_TOKEN, authenticationRequest.getUserToken());
			}
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("x-tenant", tenantId);
			HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
			responseEntity = requestMethod.toString().equalsIgnoreCase("POST")
					? restTemplate.postForEntity(uri, entity, String.class)
					: restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			if (responseEntity != null && responseEntity.getStatusCode() != null && responseEntity.getBody() != null) {
				final ApiResponse apiResponse = RestUtils.parseJSON(responseEntity.getBody(), ApiResponse.class);
				return constructResponseEntity(200, true, "Success", apiResponse.getResponseObject());
			} else {
				return constructResponseEntity(200, false, "Failed", null);

			}
		} catch (Exception exp) {
			logger.error("Exception while trying to post request :: ", exp);
		}
		return constructResponseEntity(200, true, "Failed", null);
	}

	private static WebClient getWebClient(String uri) {
		WebClient client = WebClient.create(uri);
		client.accept(APPLICATION_JSON).type(APPLICATION_JSON);
		return client;
	}

	public Map<String, String> extractHeaderToken(HttpServletRequest request) {
		String token = null;
		Map<String, String> tokens = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		String userUUID = null;

		String deviceType = request.getHeader(UserManagementConstant.DEVICE_TYPE_NAME);

		if (deviceType == null) {
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(UserManagementConstant.XSRF_TOKEN)) {
						token = userUtils.getDecryptedValue(cookie.getValue());
					} else if (cookie.getName().equals(UserManagementConstant.X_XSRF_TOKEN)) {
						userUUID = userUtils.getDecryptedValue(cookie.getValue());
					}
				}
			}
		} else {
			token = userUtils
					.getDecryptedValue(Objects.toString(request.getHeader(UserManagementConstant.XSRF_TOKEN), ""));
			userUUID = userUtils
					.getDecryptedValue(Objects.toString(request.getHeader(UserManagementConstant.X_XSRF_TOKEN), ""));
		}

		if (token == null) {
			token = request.getHeader(UserManagementConstant.XSRF_TOKEN);
		}

		if (userUUID == null) {
			userUUID = request.getHeader(UserManagementConstant.X_XSRF_TOKEN);
		}
		tokens.put(UserManagementConstant.XSRF_TOKEN, token);
		tokens.put(UserManagementConstant.X_XSRF_TOKEN, userUUID);
		return tokens;
	}

	public static String toJSON(Object object) {
		// Creating Object of ObjectMapper define in Jakson Api
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		objectMapper.getSerializerProvider().setNullKeySerializer(new MyNullKeySerializer());
		ObjectWriter objectWriter = objectMapper.writer();
		String jsonStr = null;
		try {
			jsonStr = objectWriter.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("Exception while trying to convert to JSON :: ", e);
		}
		return jsonStr;
	}

	public static <T> T parseJSON(String jsonInput, Class<T> clazz) {
		ObjectReader objectReader = new ObjectMapper().reader(clazz);
		try {
			return objectReader.readValue(jsonInput);
		} catch (IOException e) {
			logger.error("Exception while trying to parse JSON :: ", e);
		}
		return null;
	}

	public static <T> T parseJSON2(String jsonInput, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try {
			final JsonNode jsonNode = mapper.readTree(jsonInput);
//            return mapper.convertValue(jsonNode,clazz);
			return mapper.readValue(jsonNode, clazz);
		} catch (Exception e) {
			logger.error("Exception while trying to parse JSON :: ", e);
		}
		return null;
	}

	public static ResponseEntity<ApiResponse> constructResponseEntity(int code, Boolean status, String message,
			Object responseObj) {
		ApiResponse response = new ApiResponse(code, status, message, responseObj);
		ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		return responseEntity;
	}

	public static String randomWord(int length) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();

		while (salt.length() < length) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}

		String saltStr = salt.toString();
		return saltStr;
	}

}

class MyNullKeySerializer extends JsonSerializer<Object> {
	@Override
	public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused)
			throws IOException, JsonProcessingException {
		jsonGenerator.writeFieldName("");
	}
}
