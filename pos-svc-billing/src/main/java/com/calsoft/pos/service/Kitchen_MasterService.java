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
import com.calsoft.pos.model.Kitchen_MasterModel;
import com.calsoft.pos.repository.Kitchen_MasterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Kitchen_MasterService {
	Logger logger = LoggerFactory.getLogger(Kitchen_MasterService.class);

	@Autowired
	Kitchen_MasterRepository kitchen_MasterRepository;

	public Page<Kitchen_MasterModel> fetchAllKitchen(Pageable pageable, String tenantId) {

		return kitchen_MasterRepository.findByTenantId(pageable, tenantId);

	}

	public Kitchen_MasterModel findByKitchen(String kitchenId, String tenantId) {
		return kitchen_MasterRepository.findByIdAndTenantId(Long.valueOf(kitchenId), tenantId);
	}

	public ResponseWrapper saveKitchen(Kitchen_MasterModel kitchen_MasterModel, String tenantId) {

		try {

			kitchen_MasterModel.setTenantId(tenantId);
			kitchen_MasterRepository.save(kitchen_MasterModel);

			return new ResponseWrapper(kitchen_MasterModel, ResponseCodes02.ADDKITCHEN_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDKITCHEN_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(kitchen_MasterModel, ResponseCodes02.ADDKITCHEN_REC_FAILURE.getCode(),
					ResponseCodes02.ADDKITCHEN_REC_FAILURE.getDescription());
		}
	}

	public Page<Kitchen_MasterModel> searchKitchen(Specification<Kitchen_MasterModel> specs, Pageable pageable) {

		return kitchen_MasterRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Kitchen_MasterModel> fetchAllKitchenList(String tenantId) {
		return kitchen_MasterRepository.findByTenantId(tenantId);
	}

}
