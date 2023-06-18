package com.calsoft.pos.account.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.calsoft.pos.account.repostiory.AccountRepostiory;


@Service
public class AccountService {
	
	@Autowired
	AccountRepostiory accountRepostiory;
	
    @Value("${endpoint_svc_admin}")
    private String endpoint_svc_admin;
	
	private static final Map<String, String> TENANT_MAP = new HashMap<String, String>();

	public String getTenantID(String origin, String source) {
		if(origin == null) {
			return null;
		}
		if(TENANT_MAP.containsKey(origin)) {
			return TENANT_MAP.get(origin);
		} else {
			String tenantId = getTenantIdFromRedis(origin);
			if(tenantId == null) {
				updateTenantIdToRedis(origin,source);
			}
			tenantId = getTenantIdFromRedis(origin);
			if(tenantId != null) {
				TENANT_MAP.put(origin, tenantId);
				return tenantId;
			} else {
				return null;
			}
		}
	}
	
	private String getTenantIdFromRedis(String origin) {
		Optional<Account> account = accountRepostiory.findById(origin);
		if(account.isPresent() && account.get().getTenantId() != null) {
			return account.get().getTenantId();
		}
		else {
			return null;
		}
	}
	
	private void updateTenantIdToRedis(String origin, String source) {
		RestTemplate restTemplate = new RestTemplate();
		String url=endpoint_svc_admin+"/api/account/indexbyDomainName?domain="+origin + "&source=" + source;
		restTemplate.getForObject(url, String.class);
	}

}
