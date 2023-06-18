package com.calsoft.pos.controller.units;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.ExpensesModel;
import com.calsoft.pos.service.ExpensesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class ExpensesController {
	@Autowired
	ExpensesService expensesService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all Expenses page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/expenses")
	public Page<ExpensesModel> fetchAllExpenses(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return expensesService.fetchAllExpenses(pageable, tenantId);
	}

	/***
	 * this method using fetch by single Expense
	 * 
	 * @param ExpenseId
	 * @return
	 */

	@GetMapping("/expenses/{id}")
	public ExpensesModel findExpensesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return expensesService.findByExpenses(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save Expenses
	 * 
	 * 
	 * @param Expenses
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/expenses")
	public ResponseWrapper saveExpenses(@RequestBody final ExpensesModel expensesModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return expensesService.saveExpenses(expensesModel, tenantId);
	}

	/***
	 * this method using Expenses
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/expenses/filter")
	public Page<ExpensesModel> searchExpenses(@SearchSpec Specification<ExpensesModel> expensesModel,
			Pageable pageable) {
		return expensesService.searchExpenses(expensesModel, pageable);
	}

	/***
	 * this method using fetch all Expenses
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/expenses/dropdown")
	public List<ExpensesModel> fetchAllExpensesList(@RequestHeader("x-tenant") String tenantId) {
		return expensesService.fetchAllExpensesList(tenantId);
	}
}
