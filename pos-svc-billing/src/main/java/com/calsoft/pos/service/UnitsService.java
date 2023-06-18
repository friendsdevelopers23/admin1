package com.calsoft.pos.service;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.Units;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.UnitsRepository;

@Service
public class UnitsService {

	@Autowired
	private UnitsRepository unitsRepository;

	public Page<Units> searchUnitsData(Specification<Units> specs, Pageable pageable) {

		return unitsRepository.findAll(Specification.where(specs), pageable);
	}

	public ResponseWrapper saveUnits(Units units, String tenantId) {
		try {
			units.setTenantId(tenantId);
			unitsRepository.save(units);

			if (units.getOperationType() != null && units.getOperationType().equalsIgnoreCase("New")) {
				return new ResponseWrapper(units, ResponseCodes02.ADD_UNITS_SUCCESS.getCode(),
						ResponseCodes02.ADD_UNITS_SUCCESS.getDescription());
			}

			return new ResponseWrapper(units, ResponseCodes02.UPDATE_UNITS_SET_SUCCESS.getCode(),
					ResponseCodes02.UPDATE_UNITS_SET_SUCCESS.getDescription());
		} catch (Throwable exp) {

			exp.printStackTrace();
			Log.error(exp);
			if (units.getOperationType() != null && units.getOperationType().equalsIgnoreCase("New")) {
				return new ResponseWrapper(units, ResponseCodes02.ADD_UNITS_FAILED.getCode(),
						ResponseCodes02.ADD_UNITS_FAILED.getDescription());
			}
			return new ResponseWrapper(units, ResponseCodes02.UPDATE_UNITS_FAILED.getCode(),
					ResponseCodes02.UPDATE_UNITS_FAILED.getDescription());

		}
	}

	public Page<Units> getAllUnitsData(Pageable pageable, String tenantId) {

		return unitsRepository.findByTenantId(pageable, tenantId);
	}

	public Units fetchById(int id, String tenantId) {
		Units units = new Units();

		try {
			units = unitsRepository.findByIdAndTenantId(id, tenantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return units;
	}

}
