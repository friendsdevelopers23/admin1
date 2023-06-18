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
import com.calsoft.pos.model.ExpensesModel;
import com.calsoft.pos.repository.ExpensesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpensesService {
	Logger logger = LoggerFactory.getLogger(ExpensesService.class);

	@Autowired
	ExpensesRepository expensesRepository;

	public Page<ExpensesModel> fetchAllExpenses(Pageable pageable, String tenantId) {

		return expensesRepository.findByTenantId(pageable, tenantId);

	}

	public ExpensesModel findByExpenses(String expensesId, String tenantId) {
		return expensesRepository.findByIdAndTenantId(Long.valueOf(expensesId), tenantId);
	}

	public ResponseWrapper saveExpenses(ExpensesModel expensesModel, String tenantId) {

		try {

			expensesModel.setTenantId(tenantId);
			expensesRepository.save(expensesModel);

			return new ResponseWrapper(expensesModel, ResponseCodes02.ADDEXPENSES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDEXPENSES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(expensesModel, ResponseCodes02.ADDEXPENSES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDEXPENSES_REC_FAILURE.getDescription());
		}
	}

	public Page<ExpensesModel> searchExpenses(Specification<ExpensesModel> specs, Pageable pageable) {

		return expensesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<ExpensesModel> fetchAllExpensesList(String tenantId) {
		return expensesRepository.findByTenantId(tenantId);
	}

}
