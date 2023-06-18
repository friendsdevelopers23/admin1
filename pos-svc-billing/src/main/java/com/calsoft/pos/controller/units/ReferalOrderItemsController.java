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

import com.calsoft.pos.model.ReferalOrderItems;
import com.calsoft.pos.service.ReferalOrderItemsService;
import com.calsoft.springsearch.anotation.SearchSpec;

@RequestMapping("/api")
@RestController
public class ReferalOrderItemsController {

	@Autowired
	ReferalOrderItemsService referalOrderItemsService;

	/***
	 * this method using fetch all referalOrderItems page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalOrderItems")
	public Page<ReferalOrderItems> fetchAllReferal(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return referalOrderItemsService.fetchAllReferal(pageable, tenantId);
	}

	/***
	 * this method using fetch by single referalOrderItems
	 * 
	 * @param ExpenseId
	 * @return
	 */

	@GetMapping("/referalOrderItems/{id}")
	public ReferalOrderItems findReferal(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return referalOrderItemsService.findByReferal(id, tenantId);
	}

	/***
	 * this method using referalOrderItems
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalOrderItems/filter")
	public Page<ReferalOrderItems> searchReferal(@SearchSpec Specification<ReferalOrderItems> referalOrderItems,
			Pageable pageable) {
		return referalOrderItemsService.searchReferal(referalOrderItems, pageable);
	}

}
