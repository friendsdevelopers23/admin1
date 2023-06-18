package com.calsoft.pos.controller.units;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calsoft.pos.model.ReferalCustomer;
import com.calsoft.pos.service.ReferalCustomerService;
import com.calsoft.springsearch.anotation.SearchSpec;

@RequestMapping("/api")
@RestController
public class ReferalCustomerController {
	@Autowired
	ReferalCustomerService referalCustomerService;

	/***
	 * this method using fetch all referalCustomer page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalCustomer")
	public Page<ReferalCustomer> fetchAllReferalCustomer(Pageable pageable,
			@RequestHeader("x-tenant") String tenantId) {
		return referalCustomerService.fetchAllReferalCustomer(pageable, tenantId);
	}

	/***
	 * this method using fetch by single referalCustomer
	 * 
	 * @param ExpenseId
	 * @return
	 */

	@GetMapping("/referalCustomer/{id}")
	public ReferalCustomer findReferalCustomer(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return referalCustomerService.findByReferalCustomer(id, tenantId);
	}

	/***
	 * this method using referalCustomer
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalCustomer/filter")
	public Page<ReferalCustomer> searchReferalCustomer(@SearchSpec Specification<ReferalCustomer> referalCustomer,
			Pageable pageable) {
		return referalCustomerService.searchReferalCustomer(referalCustomer, pageable);
	}

}
