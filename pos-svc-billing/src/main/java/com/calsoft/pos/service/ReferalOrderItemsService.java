package com.calsoft.pos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ReferalOrderItems;
import com.calsoft.pos.repository.ReferalOrderItemsRepository;

@Service
public class ReferalOrderItemsService {
	Logger logger = LoggerFactory.getLogger(ReferalOrderItemsService.class);
	@Autowired
	ReferalOrderItemsRepository referalOrderItemsRepository;

	public Page<ReferalOrderItems> fetchAllReferal(Pageable pageable, String tenantId) {

		return referalOrderItemsRepository.findByTenantId(pageable, tenantId);

	}

	public ReferalOrderItems findByReferal(String referalOrderItemsId, String tenantId) {
		return referalOrderItemsRepository.findByItemIdAndTenantId(Long.valueOf(referalOrderItemsId), tenantId);
	}

	public Page<ReferalOrderItems> searchReferal(Specification<ReferalOrderItems> specs, Pageable pageable) {

		return referalOrderItemsRepository.findAll(Specification.where(specs), pageable);
	}

}
