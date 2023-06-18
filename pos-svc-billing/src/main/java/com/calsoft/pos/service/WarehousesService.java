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
import com.calsoft.pos.model.WarehousesModel;
import com.calsoft.pos.repository.WarehousesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WarehousesService {
	Logger logger = LoggerFactory.getLogger(WarehousesService.class);

	@Autowired
	WarehousesRepository warehousesRepository;

	public Page<WarehousesModel> fetchAllWarehouses(Pageable pageable, String tenantId) {

		return warehousesRepository.findByTenantId(pageable, tenantId);

	}

	public WarehousesModel findByWarehouses(String warehousesId, String tenantId) {
		return warehousesRepository.findByIdAndTenantId(Long.valueOf(warehousesId), tenantId);
	}

	public ResponseWrapper saveWarehouses(WarehousesModel warehousesModel, String tenantId) {

		try {

			warehousesModel.setTenantId(tenantId);
			warehousesRepository.save(warehousesModel);

			return new ResponseWrapper(warehousesModel, ResponseCodes02.ADDWAREHOUSES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDWAREHOUSES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(warehousesModel, ResponseCodes02.ADDWAREHOUSES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDWAREHOUSES_REC_FAILURE.getDescription());
		}
	}

	public Page<WarehousesModel> searchWarehouses(Specification<WarehousesModel> specs, Pageable pageable) {

		return warehousesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<WarehousesModel> fetchAllWarehousesList(String tenantId) {
		return warehousesRepository.findByTenantId(tenantId);
	}
}
