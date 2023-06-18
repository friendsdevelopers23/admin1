package com.calsoft.pos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ReferalCustomer;
import com.calsoft.pos.repository.ReferalCustomerRepository;

@Service
public class ReferalCustomerService {
	Logger logger = LoggerFactory.getLogger(ReferalCustomerService.class);
	@Autowired
	ReferalCustomerRepository referalCustomerRepository;

	public Page<ReferalCustomer> fetchAllReferalCustomer(Pageable pageable, String tenantId) {

		return referalCustomerRepository.findByTenantId(pageable, tenantId);

	}

	public ReferalCustomer findByReferalCustomer(String referalCustomerId, String tenantId) {
		return referalCustomerRepository.findByProductIdAndTenantId(Long.valueOf(referalCustomerId), tenantId);
	}

	public Page<ReferalCustomer> searchReferalCustomer(Specification<ReferalCustomer> specs, Pageable pageable) {

		return referalCustomerRepository.findAll(Specification.where(specs), pageable);
	}

}
