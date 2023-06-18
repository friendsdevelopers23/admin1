package com.calsoft.security.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.calsoft.pos.usermanagement.authentication.utils.AuthenticationServer;
import com.calsoft.security.config.httpwrapper.HeaderMapRequestWrapper;
import com.calsoft.security.config.httpwrapper.XSSRequestWrapper;
import com.calsoft.utils.CommonUtils;
import com.calsoft.utils.UserManagementConstant;
import com.google.gson.JsonObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebFilter extends OncePerRequestFilter {

	private SecretKeySpec secretKeySpec;

	public static final List<String> ALLOWDED_METHODS = new ArrayList<>(
			Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		final String JWT_TOKEN_SECRETKEY = EnvProperties.getConfigValue("JWT_TOKEN_SECRETKEY");
		final String secureCookie = EnvProperties.getConfigValue("calsoft.secure.cookie");
		final String secretKey = EnvProperties.getConfigValue("secret_key");
		AuthenticationServer authenticationServer = new AuthenticationServer();
		JsonObject responseObject;
		String allowdedHeaders = "Origin, X-Requested-With, Content-Type, Accept, cci, cxi, withCredentials,deviceType";
		String deviceType = request.getHeader(UserManagementConstant.DEVICE_TYPE_NAME);
		String authNTokenHeader = Objects.toString(request.getHeader(UserManagementConstant.XSRF_TOKEN), "");
		String userUUID = Objects.toString(request.getHeader(UserManagementConstant.X_XSRF_TOKEN), "");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE,HEAD");
		if ("OPTIONS".equals(request.getMethod())) {
			response.addHeader("Access-Control-Allow-Headers", allowdedHeaders);
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		} else if (ALLOWDED_METHODS.contains(request.getMethod())) {

			HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);

			if ((authNTokenHeader == null || authNTokenHeader.isEmpty()) && deviceType == null) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {

					for (Cookie cookie : cookies) {
						if (cookie.getName().equalsIgnoreCase(UserManagementConstant.XSRF_TOKEN)) {

							String decryptedTextXsrfToken = cookie.getValue();
							try {

								Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
								secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
								cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
								byte[] encryptedText = Base64.decodeBase64(decryptedTextXsrfToken);
								byte[] plainText = cipher.doFinal(encryptedText);
								decryptedTextXsrfToken = new String(plainText);
							} catch (Exception e) {
								e.printStackTrace();
							}
							authNTokenHeader = decryptedTextXsrfToken;
							break;
						} 
						
						
					}
					
					if (Objects.toString(request.getHeader(UserManagementConstant.X_XSRF_TOKEN), "")!=null) {
						String decryptedTextX_XsrfToken = Objects.toString(request.getHeader(UserManagementConstant.X_XSRF_TOKEN), "");
						try {

							Cipher cipherXsrf = Cipher.getInstance("AES/ECB/PKCS5Padding");
							secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
							cipherXsrf.init(Cipher.DECRYPT_MODE, secretKeySpec);
							byte[] encryptedText = Base64.decodeBase64(decryptedTextX_XsrfToken);
							byte[] plainText = cipherXsrf.doFinal(encryptedText);
							decryptedTextX_XsrfToken = new String(plainText);
						} catch (Exception e) {
							e.printStackTrace();
						}
						userUUID = decryptedTextX_XsrfToken;
					}
				}
			} else if (deviceType != null && deviceType.equalsIgnoreCase(UserManagementConstant.DEVICE_TYPE)) {
				String decryptedTextXsrfToken = authNTokenHeader;

				try {

					Cipher cipherDecryptedTextXsrfToken = Cipher.getInstance("AES/ECB/PKCS5Padding");
					secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
					cipherDecryptedTextXsrfToken.init(Cipher.DECRYPT_MODE, secretKeySpec);
					byte[] encryptedText = Base64.decodeBase64(decryptedTextXsrfToken);
					byte[] plainText = cipherDecryptedTextXsrfToken.doFinal(encryptedText);
					decryptedTextXsrfToken = new String(plainText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				authNTokenHeader = decryptedTextXsrfToken;

				String decryptedTextX_XsrfToken = userUUID;
				try {

					Cipher cipherDecryptedTextXXsrfToken = Cipher.getInstance("AES/ECB/PKCS5Padding");
					secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
					cipherDecryptedTextXXsrfToken.init(Cipher.DECRYPT_MODE, secretKeySpec);
					byte[] encryptedText = Base64.decodeBase64(decryptedTextX_XsrfToken);
					byte[] plainText = cipherDecryptedTextXXsrfToken.doFinal(encryptedText);
					decryptedTextX_XsrfToken = new String(plainText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				userUUID = decryptedTextX_XsrfToken;

			}

			if (userUUID == null) {
				userUUID = Objects.toString(request.getHeader(UserManagementConstant.X_XSRF_TOKEN), "");
			}

			boolean verifyTokenMatched = authenticationServer.getJwtToken(userUUID,authNTokenHeader);

			if (authNTokenHeader == null || authNTokenHeader.trim().length() == 0) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
				return;
			} else if (!verifyTokenMatched) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
				return;
			} else {
				try {
					if (authenticationServer.isTokenExpired(authNTokenHeader)) {
						if (deviceType != null && deviceType.equalsIgnoreCase(UserManagementConstant.DEVICE_TYPE)) {
							response.setStatus(HttpServletResponse.SC_GONE);
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							responseObject = new JsonObject();
							responseObject.addProperty("error_code", 003);
							responseObject.addProperty("error_message", "Renew Access Token");
							response.getWriter().write(responseObject.toString());
							return;
						} else {
							String refreshToken = authenticationServer.getRefreshToken(authNTokenHeader, userUUID);
							if (refreshToken != null) {
								if (!authenticationServer.isTokenExpired(refreshToken)) {
									String accessToken = authenticationServer.renewAccessToken(refreshToken);

									if (accessToken != null) {

										authenticationServer.removePreviousAccesToken(accessToken,userUUID);
										String encryptedString = null;
										try {
											Cipher cipherAccessToken = Cipher.getInstance("AES/ECB/PKCS5Padding");
											secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
											cipherAccessToken.init(Cipher.ENCRYPT_MODE, secretKeySpec);
											byte[] plainText = accessToken.getBytes("UTF8");
											byte[] encryptedText = cipherAccessToken.doFinal(plainText);
											encryptedString = new String(Base64.encodeBase64(encryptedText));
										} catch (Exception e) {
											e.printStackTrace();
										}

										String source = authenticationServer.getValueFromToken(accessToken, "source");

										int maxAge = 0;

										if (CommonUtils.SOURCE_ADMIN.equalsIgnoreCase(source)) {

											maxAge = -1;
										} else {
											maxAge = UserManagementConstant.MAX_AGE;
										}

										Cookie cookie = new Cookie(UserManagementConstant.XSRF_TOKEN, encryptedString);
										cookie.setPath("/");
										cookie.setHttpOnly(Boolean.valueOf(secureCookie));
										cookie.setSecure(Boolean.valueOf(secureCookie));
										cookie.setMaxAge(maxAge);
										response.addCookie(cookie);
										Claims claims = Jwts.parser().setSigningKey(JWT_TOKEN_SECRETKEY)
												.parseClaimsJws(accessToken).getBody();
										request.setAttribute("claims", claims);

										SecurityContextHolder.getContext().setAuthentication(getAuthorization(claims));
										filterChain.doFilter(new XSSRequestWrapper(requestWrapper), response);
										return;
									} else {
										response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
										response.setContentType("application/json");
										response.setCharacterEncoding("UTF-8");
										responseObject = new JsonObject();
										responseObject.addProperty("error_code", 0001);
										responseObject.addProperty("error_message", "Invalid Access Token");
										response.getWriter().write(responseObject.toString());
										return;
									}
								} else {
									response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
									response.setContentType("application/json");
									response.setCharacterEncoding("UTF-8");
									responseObject = new JsonObject();
									responseObject.addProperty("error_code",
											UserManagementConstant.EXCEPTION_LOGOUT_CODE);
									responseObject.addProperty("error_message", "Login Requried");
									response.getWriter().write(responseObject.toString());
									return;
								}
							} else {
								response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
								response.setContentType("application/json");
								response.setCharacterEncoding("UTF-8");
								responseObject = new JsonObject();
								responseObject.addProperty("error_code", UserManagementConstant.EXCEPTION_LOGOUT_CODE);
								responseObject.addProperty("error_message", "Login Required");
								response.getWriter().write(responseObject.toString());
								return;
							}
						}
					} else {
						if (!authenticationServer.isValidToken(authNTokenHeader, userUUID)) {
							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							responseObject = new JsonObject();
							responseObject.addProperty("error_code", 0004);
							responseObject.addProperty("error_message", "Tampered Access Token");
							response.getWriter().write(responseObject.toString());
							return;
						} else {
							Claims claims = Jwts.parser().setSigningKey(JWT_TOKEN_SECRETKEY)
									.parseClaimsJws(authNTokenHeader).getBody();
							request.setAttribute("claims", claims);
							SecurityContextHolder.getContext().setAuthentication(getAuthorization(claims));
							filterChain.doFilter(new XSSRequestWrapper(requestWrapper), response);
						}
					}
				}

				catch (SignatureException e) {
					// ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED,
					// "Exception Occured");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseObject = new JsonObject();
					responseObject.addProperty("error_code", 0005);
					responseObject.addProperty("error_message", "Exception Occured");
					response.getWriter().write(responseObject.toString());
					return;
				}
			}
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			responseObject = new JsonObject();
			responseObject.addProperty("error_code", 0006);
			responseObject.addProperty("error_message", "Method Not Found!");
			response.getWriter().write(responseObject.toString());
			return;
		}
	}

	public Authentication getAuthorization(Claims claims) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
		try {
			if (claims.get("roles") != null) {
				String rolesStr = claims.get("roles").toString();
				JSONArray roles = new JSONArray(rolesStr);
				for (Object role : roles) {
					authorities.add(new SimpleGrantedAuthority(role.toString()));
				}
			}
			User principal = new User(claims.getIssuer(), "", authorities);
			usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, "", authorities);
		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
		return usernamePasswordAuthenticationToken;
	}

}
