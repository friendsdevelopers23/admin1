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
import com.calsoft.pos.model.Variants;
import com.calsoft.pos.repository.VariantsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VariantsService {
	Logger logger = LoggerFactory.getLogger(VariantsService.class);
	@Autowired
	VariantsRepository variantsRepository;

	public Page<Variants> fetchAllVariants(Pageable pageable, String tenantId) {

		return variantsRepository.findByTenantId(pageable, tenantId);

	}

	public Variants findByVariants(String variantsId, String tenantId) {
		return variantsRepository.findByIdAndTenantId(Long.valueOf(variantsId), tenantId);
	}

	public ResponseWrapper saveVariants(Variants variants, String tenantId) {

		try {

			variants.setTenantId(tenantId);
			variantsRepository.save(variants);

			return new ResponseWrapper(variants, ResponseCodes02.ADDVARIANTS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDVARIANTS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(variants, ResponseCodes02.ADDVARIANTS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDVARIANTS_REC_FAILURE.getDescription());
		}
	}

	public Page<Variants> searchVariants(Specification<Variants> specs, Pageable pageable) {

		return variantsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Variants> fetchAllVariantsList(String tenantId) {
		return variantsRepository.findByTenantId(tenantId);
	}
}
