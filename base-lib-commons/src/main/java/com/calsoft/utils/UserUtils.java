package com.calsoft.utils;

import java.util.Base64;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserUtils {

	@Autowired
	EncryptionManagerCookie ecryptionManagerCookie;

	public String getUserIdFromToken(String token) {
//		Claims claims;
//		String JWT_TOKEN_SECRETKEY = EnvProperties.getConfigValue("JWT_TOKEN_SECRETKEY");

		JSONObject result = null;
		try {

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = token.split("\\.");
			result = new JSONObject(new String(decoder.decode(parts[1])));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return result.get("user-id").toString();
	}

	public String getToken(HttpServletRequest request) {

		String token = null;

		String deviceType = request.getHeader(UserManagementConstant.DEVICE_TYPE_NAME);

		if (deviceType != null && request.getHeader(UserManagementConstant.DEVICE_TYPE_NAME)
				.equalsIgnoreCase(UserManagementConstant.DEVICE_TYPE)) {
			token = getDecryptedValue(request.getHeader(UserManagementConstant.XSRF_TOKEN));

		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(UserManagementConstant.XSRF_TOKEN)) {
						token = getDecryptedValue(cookie.getValue());
					}
				}
			}

			if (token == null) {
				token = request.getHeader("xsrf-token");
			}

		}

		return token;
	}

	public String getDecryptedValue(String value) {
		try {
			return ecryptionManagerCookie.decrypt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public Map<String, String> getValueForMap(Map<String, String> cookieValue, Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(UserManagementConstant.XSRF_TOKEN)) {
				cookieValue.put(UserManagementConstant.XSRF_TOKEN, getDecryptedValue(cookie.getValue()));
			} else if (cookie.getName().equalsIgnoreCase(UserManagementConstant.X_XSRF_TOKEN)) {
				cookieValue.put(UserManagementConstant.X_XSRF_TOKEN, getDecryptedValue(cookie.getValue()));
			}
		}
		return cookieValue;
	}

	public String getUserNameFromToken(String token) {
		JSONObject result = null;
		try {

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = token.split("\\.");

			result = new JSONObject(new String(decoder.decode(parts[1])));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return result.get("iss").toString();
	}

	public String getSupplierId(String token) {
		JSONObject result = null;
		try {

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = token.split("\\.");

			result = new JSONObject(new String(decoder.decode(parts[1])));

		} catch (Exception e) {
			log.error(e.getMessage());

		}

		if (result.get(UserManagementConstant.USER_TYPE)
				.equals(UserManagementConstant.VENDOR_USER)) {
			return result.get(UserManagementConstant.VST).toString();
		} else {
			return "0";
		}

	}

	public static String generateOTP(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		return String.valueOf(otp);
	}

}
