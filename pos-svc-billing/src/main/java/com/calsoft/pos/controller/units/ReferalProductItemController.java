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

import com.calsoft.pos.model.ReferalProductItem;
import com.calsoft.pos.service.ReferalProductItemService;
import com.calsoft.springsearch.anotation.SearchSpec;

@RequestMapping("/api")
@RestController
public class ReferalProductItemController {
	@Autowired
	ReferalProductItemService referalProductItemService;

	/***
	 * this method using fetch all referalProductItem page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalProductItem")
	public Page<ReferalProductItem> fetchAllReferal(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return referalProductItemService.fetchAllReferal(pageable, tenantId);
	}

	/***
	 * this method using fetch by single referalProductItem
	 * 
	 * @param ExpenseId
	 * @return
	 */

	@GetMapping("/referalProductItem/{id}")
	public ReferalProductItem findReferal(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return referalProductItemService.findByReferal(id, tenantId);
	}

	/***
	 * this method using referalProductItem
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/referalProductItem/filter")
	public Page<ReferalProductItem> searchReferal(@SearchSpec Specification<ReferalProductItem> referalProductItem,
			Pageable pageable) {
		return referalProductItemService.searchReferal(referalProductItem, pageable);
	}

}
