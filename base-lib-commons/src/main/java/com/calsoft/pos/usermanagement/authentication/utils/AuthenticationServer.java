package com.calsoft.pos.usermanagement.authentication.utils;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.calsoft.pos.config.CacheServer;
import com.calsoft.security.config.EnvProperties;
import com.calsoft.utils.UserManagementConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationServer {

	public boolean isTokenExpired(String token) {
		boolean isExpired = false;
		try {
			final Date expiration = getExpirationDateFromToken(token);
			if (expiration != null)
				isExpired = false;
			else
				isExpired = true;
			log.debug("Token Expired: " + isExpired);
		} catch (Exception e) {
			log.error("Invalid Token:  " + token + " : " + e.getMessage());
		}
		return isExpired;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
			log.error("Token Expired:  " + token + " : " + e.getMessage());
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		String JWT_TOKEN_SECRETKEY = EnvProperties.getConfigValue("JWT_TOKEN_SECRETKEY");
		try {
			claims = Jwts.parser().setSigningKey(JWT_TOKEN_SECRETKEY).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error("Expired Token: " + token + e.getMessage());
			claims = null;
		}
		return claims;
	}

	public String getRefreshToken(String token, String uuid) {
		log.info("getRefreshToken() method started...");
		String refreshToken = null;
		CacheServer cacheServer = ApplicationContextProvider.getApplicationContext().getBean(CacheServer.class);
		try {
			final JSONObject claims = getClaimsFromExpiredTokenToken(token);
			String userUUID = claims.getString("UUID");
			if (uuid.equalsIgnoreCase(userUUID)) {
				Object temp = cacheServer.get("user::" + uuid + "::" + UserManagementConstant.TOKEN_REFRESH);
				if (temp != null) {
					refreshToken = temp.toString();
				}
			}
		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
		return refreshToken;
	}

	public JSONObject getClaimsFromExpiredTokenToken(String token) {
		log.info("getClaimsFromExpiredTokenToken() method started...");
		JSONObject result = null;
		try {
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = token.split("\\.");
			result = new JSONObject(new String(decoder.decode(parts[1])));
		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
		return result;
	}

	public String renewAccessToken(String refreshToken) {
		log.info("renewAccessToken() method started...");
		String accessToken = null;
		String JWT_TOKEN_VALIDITY = EnvProperties.getConfigValue("JWT_TOKEN_VALIDITY");
		try {
			final Claims claims = getClaimsFromToken(refreshToken);
			String accessTokenValidity = JWT_TOKEN_VALIDITY.split(":")[0];
			TokenService tokenService = (TokenService) ApplicationContextProvider.getApplicationContext()
					.getBean("tokenService");

			accessToken = tokenService.generateJWTToken(claims.get(UserManagementConstant.TOKEN_UUID).toString(),
					claims.getIssuer(), (List<String>) claims.get("roles"), claims.getSubject(),
					Long.valueOf(accessTokenValidity), claims.get("user-id").toString(),
					claims.get("source").toString(), claims.get(UserManagementConstant.VST).toString(),
					claims.get(UserManagementConstant.USER_TYPE).toString());

//			accessToken = tokenService.generateJWTToken(claims.get(UserManagementConstant.TOKEN_UUID).toString(),
//					claims.getIssuer(), (List<String>) claims.get("roles"), claims.getSubject(), Long.valueOf(accessTokenValidity));

		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
		return accessToken;
	}

	public boolean isValidToken(String authNTokenHeader, String userUUID) {
		boolean isValidToken = false;
		try {
			final Claims claims = getClaimsFromToken(authNTokenHeader);
			String userUUIDBytoken = claims.get(UserManagementConstant.TOKEN_UUID).toString();
			if (userUUIDBytoken.equals(userUUID)) {
				isValidToken = true;
			}
			log.debug("Is Valid Token: " + isValidToken);
		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
		return isValidToken;
	}

	public String getValueFromToken(String token, String parameter) {
		JSONObject result = null;
		try {

			Base64.Decoder decoder = Base64.getUrlDecoder();
			String[] parts = token.split("\\.");
			result = new JSONObject(new String(decoder.decode(parts[1])));

		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return result.get(parameter).toString();
	}

	public boolean getJwtToken(String token, String authNTokenHeader) {

		CacheServer cacheServer = ApplicationContextProvider.getApplicationContext().getBean(CacheServer.class);

		String key = "user::" + token + "::" + UserManagementConstant.XSRF_TOKEN;

		Object keys = cacheServer.get(key);

		if (keys == null) {
			return false;
		}

		JSONObject value = getClaimsFromExpiredTokenToken(keys.toString());

		String uuid = value.get("UUID").toString();

		if (uuid.equalsIgnoreCase(token)) {
			return true;
		} else {
			return false;
		}

	}

	public void removePreviousAccesToken(String accessToken, String userUUID) {
		CacheServer cacheServer = ApplicationContextProvider.getApplicationContext().getBean(CacheServer.class);

		try {
			String key = "user::" + userUUID + "::" + UserManagementConstant.XSRF_TOKEN;

			String refreshTokenValidity = EnvProperties.getConfigValue("JWT_TOKEN_VALIDITY").split(":")[1];

			cacheServer.setExpiryTime(key, accessToken, Long.valueOf(refreshTokenValidity));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
