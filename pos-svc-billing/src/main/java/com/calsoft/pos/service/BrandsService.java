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
import com.calsoft.pos.model.BrandsModel;
import com.calsoft.pos.repository.BrandsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandsService {

	Logger logger = LoggerFactory.getLogger(BrandsService.class);

	@Autowired
	BrandsRepository brandsRepository;

	public Page<BrandsModel> fetchAllBrands(Pageable pageable, String tenantId) {

		return brandsRepository.findByTenantId(pageable, tenantId);

	}

	public BrandsModel findByBrands(String brandsId, String tenantId) {
		return brandsRepository.findByIdAndTenantId(Long.valueOf(brandsId), tenantId);
	}

	public ResponseWrapper saveBrands(BrandsModel brandsModel, String tenantId) {

		try {

			brandsModel.setTenantId(tenantId);
			brandsRepository.save(brandsModel);

			return new ResponseWrapper(brandsModel, ResponseCodes02.ADDWAREHOUSES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDWAREHOUSES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(brandsModel, ResponseCodes02.ADDWAREHOUSES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDWAREHOUSES_REC_FAILURE.getDescription());
		}
	}

	public Page<BrandsModel> searchBrands(Specification<BrandsModel> specs, Pageable pageable) {

		return brandsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<BrandsModel> fetchAllBrandsList(String tenantId) {
		return brandsRepository.findByTenantId(tenantId);
	}
}
