package com.calsoft.pos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ReferalProductItem;
import com.calsoft.pos.repository.ReferalProductItemRepository;

@Service
public class ReferalProductItemService {
	Logger logger = LoggerFactory.getLogger(ReferalProductItemService.class);
	@Autowired
	ReferalProductItemRepository referalProductItemRepository;

	public Page<ReferalProductItem> fetchAllReferal(Pageable pageable, String tenantId) {

		return referalProductItemRepository.findByTenantId(pageable, tenantId);

	}

	public ReferalProductItem findByReferal(String referalProductItemId, String tenantId) {
		return referalProductItemRepository.findByProductIdAndTenantId(Long.valueOf(referalProductItemId), tenantId);
	}

	public Page<ReferalProductItem> searchReferal(Specification<ReferalProductItem> specs, Pageable pageable) {

		return referalProductItemRepository.findAll(Specification.where(specs), pageable);
	}

}
