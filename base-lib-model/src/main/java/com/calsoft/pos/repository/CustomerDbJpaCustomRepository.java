package com.calsoft.pos.repository;

import java.util.List;

import com.calsoft.pos.model.customerindex.CustomerDb;


public interface CustomerDbJpaCustomRepository {
	
	List<CustomerDb> findbyCustomerId(Integer entityId, String tenantId) throws Exception;

}
