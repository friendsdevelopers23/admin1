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

import com.calsoft.pos.model.ApproveTransactinos;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.ApproveTransactinosService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class ApproveTransactinosController {
	@Autowired
	ApproveTransactinosService approveTransactinosService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all approveTransactinos page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/approvetransactinos")
	public Page<ApproveTransactinos> fetchAllApproveTransactinos(Pageable pageable,
			@RequestHeader("x-tenant") String tenantId) {
		return approveTransactinosService.fetchAllApproveTransactinos(pageable, tenantId);
	}

	/***
	 * this method using fetch by single approveTransactinos
	 * 
	 * @param ExpenseId
	 * @return
	 */

	@GetMapping("/approvetransactinos/{id}")
	public ApproveTransactinos findApproveTransactinosId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return approveTransactinosService.findByApproveTransactinos(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save approveTransactinos
	 * 
	 * 
	 * @param Expenses
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/approvetransactinos")
	public ResponseWrapper saveApproveTransactinos(@RequestBody final ApproveTransactinos ApproveTransactinos,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return approveTransactinosService.saveApproveTransactinos(ApproveTransactinos, tenantId);
	}

	/***
	 * this method using approveTransactinos
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/approvetransactinos/filter")
	public Page<ApproveTransactinos> searchApproveTransactinos(
			@SearchSpec Specification<ApproveTransactinos> approveTransactinos, Pageable pageable) {
		return approveTransactinosService.searchApproveTransactinos(approveTransactinos, pageable);
	}

	/***
	 * this method using fetch all approveTransactinos
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/approvetransactinos/dropdown")
	public List<ApproveTransactinos> fetchAllApproveTransactinosList(@RequestHeader("x-tenant") String tenantId) {
		return approveTransactinosService.fetchAllApproveTransactinosList(tenantId);
	}
}
