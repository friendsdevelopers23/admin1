package com.calsoft.pos.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.calsoft.pos.account.model.AccountService;
import com.calsoft.pos.account.repostiory.AccountRepostiory;
import com.calsoft.pos.utils.UserManagementConstant;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class RequestFilter {

	@Value("${endpoint_svc_admin}")
	private String endpoint_svc_admin;

	@Value("${endpoint_svc_catalog}")
	private String endpoint_svc_catalog;

	@Value("${endpoint_svc_core}")
	private String endpoint_svc_core;

	@Value("${endpoint_svc_sales}")
	private String endpoint_svc_sales;

	@Value("${endpoint_svc_billing}")
	private String endpoint_svc_billing;

	@Value("${endpoint_svc_deploy}")
	private String endpoint_svc_deploy;
	
	@Value("${endpoint_svc_social}")
	private String endpoint_svc_social;

	@Value("${admin.url}")
	private String adminUrl;

	@Autowired
	EncryptionManager encryptionManager;

	@Autowired
	AccountRepostiory accountRepostiory;

	@Autowired
	AccountService accountService;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/pos-gateway/pos-svc-admin-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_admin))
				.route(r -> r.path("/pos-gateway/pos-svc-catalog-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_catalog))
				.route(r -> r.path("/pos-gateway/pos-svc-core-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_core))
				.route(r -> r.path("/pos-gateway/pos-svc-billing-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_billing))
				.route(r -> r.path("/pos-gateway/pos-svc-sales-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_sales))
				.route(r -> r.path("/pos-gateway/pos-svc-deploy-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_deploy))
				.route(r -> r.path("/pos-gateway/pos-svc-social-1.0-SNAPSHOT/**").filters(customheaderFilter())
						.uri(endpoint_svc_social))
				.build();
	}

	private Function<GatewayFilterSpec, UriSpec> customheaderFilter() {
		return f -> f.stripPrefix(1).preserveHostHeader().filters(this::addTenantHeaders);

//				.modifyRequestBody(String.class, String.class, MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> {
//					String resp = null;
//					try {
//						return Mono.just(encryptionManager.decrypt(s));
//					} catch (Exception e) {
//						resp = "{}";
//						log.error("Exception in gateway filer: " + e.getMessage());
//						log.error("Exception in gateway filer: ", e);
//					}
//
//					return Mono.just(resp);
//				});
	}

	private Mono<Void> addTenantHeaders(ServerWebExchange exchange, GatewayFilterChain chain) {
		try {
			HttpHeaders _headers = exchange.getRequest().getHeaders();

			List<String> deviceType = _headers.get(UserManagementConstant.DEVICE_TYPE_NAME);
			List<String> authority = _headers.get("origin");
			List<String> urlArray = new ArrayList<String>();
			String url = exchange.getRequest().getURI().toString();
			urlArray.add(url.trim());
			log.error("orgin" + url.trim());
		
			List<String> referers =deviceType != null ? _headers.get(UserManagementConstant.ENTERPRISE_SITTE)
					:_headers.get("x-requested-with")!= null ?urlArray 	: _headers.get("referer") != null ? _headers.get("referer") : urlArray;
			if (referers != null) {
				
				
				for (String referer : referers) {
					referer = referer.replace("https://", "");
					referer = referer.replace("http://", "");
					if (referer.contains("/")) {
						referer = referer.split("/")[0];
					}
					String origin = referer;
					
					log.error("orgin" + referer);
					exchange.getRequest().mutate().headers(headers -> {
						String tenantId = null;
						String source = null;
						if (origin.equalsIgnoreCase(adminUrl)) {

							String value = null;

							List<String> tenantName = _headers.get(UserManagementConstant.ENTERPRISE_NAME);

							if (tenantName == null) {
								MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();

								List<HttpCookie> enterPriseName = cookies.get(UserManagementConstant.ENTERPRISE_NAME);

								value = String.valueOf(enterPriseName.get(0)).replace("c-id=", "");
							} else {
								value = tenantName.get(0);
							}

							if (value != null) {
								value = encryptionManager.decrypt(value);
							}
							if (value != null) {
								source = UserManagementConstant.SOURCE_ADMIN;

								value = value.substring(1, value.length() - 1);
								tenantId = accountService.getTenantID(value, source);
							}

						} else {
							source = UserManagementConstant.SOURCE_SITE;
							tenantId = accountService.getTenantID(origin, source);
						}

						if (tenantId == null) {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
									"Account Information not found Here!"+origin);
						}

						String requestURl = exchange.getRequest().getURI().toString();
						log.error("tenantId" + tenantId);
						if (requestURl.contains("translate/filter?search=")) {
							headers.remove("x-tenant");
						} else if (requestURl.contains("account/filter?search=")) {
							headers.remove("x-tenant");
						} else {
							headers.add("x-tenant", tenantId);
						}

					}).build();
				}
			}

			else {
				log.error("errror" + referers);
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account Information not found!");
			}

		} catch (Exception e) {

			log.error("Exception in gateway filer: " + e.getMessage());
			log.error("Exception in gateway filer: ", e);
		}
		return chain.filter(exchange);
	}
}