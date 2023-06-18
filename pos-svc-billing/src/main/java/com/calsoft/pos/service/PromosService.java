package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.model.Promos;
import com.calsoft.pos.repository.PromosRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromosService {
	Logger logger = LoggerFactory.getLogger(PromosService.class);
	@Autowired
	PromosRepository promosRepository;

	public Page<Promos> fetchAllPromos(Pageable pageable, String tenantId) {

		return promosRepository.findByTenantId(pageable, tenantId);

	}

	public Promos findByPromos(String promosId, String tenantId) {
		return promosRepository.findByIdAndTenantId(Long.valueOf(promosId), tenantId);
	}

	public ResponseWrapper savePromos(Promos promos, String tenantId) {

		try {

			promos.setTenantId(tenantId);
			promosRepository.save(promos);

			return new ResponseWrapper(promos, ResponseCodes02.ADDKITCHEN_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDKITCHEN_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(promos, ResponseCodes02.ADDKITCHEN_REC_FAILURE.getCode(),
					ResponseCodes02.ADDKITCHEN_REC_FAILURE.getDescription());
		}
	}

	public Page<Promos> searchPromos(Specification<Promos> specs, Pageable pageable) {

		return promosRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Promos> fetchAllPromosList(String tenantId) {
		return promosRepository.findByTenantId(tenantId);
	}
}
