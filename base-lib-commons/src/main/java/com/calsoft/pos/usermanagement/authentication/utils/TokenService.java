package com.calsoft.pos.usermanagement.authentication.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.calsoft.utils.UserManagementConstant;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenService {

	@Value("${JWT_TOKEN_SECRETKEY}")
	String JWT_TOKEN_SECRETKEY;

	@Value("${JWT_TOKEN_VALIDITY}")
	String JWT_TOKEN_VALIDITY;

	public String generateJWTToken(String userUUID, String issuer, List<String> profiles, String subject,
			long ttlMillisinMin, String userId, String source,String supplierId, String userType) {
		log.info("generateJWTToken() method started...");
		JwtBuilder builder = null;
		try {
			// The JWT signature algorithm we will be using to sign the token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			long nowMillis = calendar.getTimeInMillis();

			Date now = new Date(nowMillis);
			// Let's set the JWT Claims
//			builder = Jwts.builder().setId(userUUID).setIssuedAt(now).claim("roles", profiles)
//					.claim(UserManagementConstant.TOKEN_UUID, userUUID).setSubject(subject).setIssuer(issuer)
//					.signWith(signatureAlgorithm, JWT_TOKEN_SECRETKEY);

			builder = Jwts.builder().setId(userUUID).setIssuedAt(now).claim("roles", profiles).claim("user-id", userId)
					.claim("source", source).claim(UserManagementConstant.TOKEN_UUID, userUUID).setSubject(subject)
					.claim("ss", now)
					.claim(UserManagementConstant.VST, supplierId)
					.claim(UserManagementConstant.USER_TYPE, userType)
					.setIssuer(issuer).signWith(signatureAlgorithm, JWT_TOKEN_SECRETKEY);

			// if it has been specified, let's add the expiration
			if (ttlMillisinMin >= 0) {
				long expireMinm = getExpireUTCTimeStamp((int) ttlMillisinMin);
				Date exp = new Date(expireMinm);
				builder.setExpiration(exp);
			}

		} catch (Exception e) {
			log.error("Exception at: ", e);
		} finally {
			log.info("generateJWTToken() method ended...");
		}
		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	private long getExpireUTCTimeStamp(int minute) {
		Calendar calendar = null;
		try {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.add(Calendar.MINUTE, minute);
		} catch (Exception e) {
			log.error("Exception at: ", e);
		} finally {
			log.info("getExpireUTCTimeStamp() method ended...");
		}
		return calendar.getTimeInMillis();
	}
}
