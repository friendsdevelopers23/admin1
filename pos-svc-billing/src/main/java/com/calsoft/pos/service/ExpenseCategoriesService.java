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
import com.calsoft.pos.model.ExpenseCategories;
import com.calsoft.pos.repository.ExpenseCategoriesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpenseCategoriesService {

	Logger logger = LoggerFactory.getLogger(ExpenseCategoriesService.class);
	@Autowired
	ExpenseCategoriesRepository expenseCategoriesRepository;

	public Page<ExpenseCategories> fetchAllExpenseCategories(Pageable pageable, String tenantId) {

		return expenseCategoriesRepository.findByTenantId(pageable, tenantId);

	}

	public ExpenseCategories findByExpenseCategories(String expenseCategoriesId, String tenantId) {
		return expenseCategoriesRepository.findByIdAndTenantId(Long.valueOf(expenseCategoriesId), tenantId);
	}

	public ResponseWrapper saveExpenseCategories(ExpenseCategories expenseCategories, String tenantId) {

		try {

			expenseCategories.setTenantId(tenantId);
			expenseCategoriesRepository.save(expenseCategories);

			return new ResponseWrapper(expenseCategories, ResponseCodes02.ADDEXPENSE_CATEGORIES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDEXPENSE_CATEGORIES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(expenseCategories, ResponseCodes02.ADDEXPENSE_CATEGORIES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDEXPENSE_CATEGORIES_REC_FAILURE.getDescription());
		}
	}

	public Page<ExpenseCategories> searchExpenseCategories(Specification<ExpenseCategories> specs, Pageable pageable) {

		return expenseCategoriesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<ExpenseCategories> fetchAllExpenseCategoriesList(String tenantId) {
		return expenseCategoriesRepository.findByTenantId(tenantId);
	}
}
