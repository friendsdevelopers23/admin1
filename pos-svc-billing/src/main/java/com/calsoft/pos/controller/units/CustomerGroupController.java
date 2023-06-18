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

import com.calsoft.pos.model.CustomerGroup;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.CustomerGroupService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class CustomerGroupController {
	@Autowired
	CustomerGroupService customerGroupService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all customerGroup page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/customergroup")
	public Page<CustomerGroup> fetchAllCustomerGroup(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return customerGroupService.fetchAllCustomerGroup(pageable, tenantId);
	}

	/***
	 * this method using fetch by single customerGroup
	 * 
	 * @param CustomerGroupId
	 * @return
	 */

	@GetMapping("/customergroup/{id}")
	public CustomerGroup findCustomerGroupId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return customerGroupService.findByCustomerGroup(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save customerGroup
	 * 
	 * 
	 * @param customerGroup
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/customergroup")
	public ResponseWrapper saveCustomerGroup(@RequestBody final CustomerGroup CustomerGroup,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return customerGroupService.saveCustomerGroup(CustomerGroup, tenantId);
	}

	/***
	 * this method using customerGroup
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/customergroup/filter")
	public Page<CustomerGroup> searchCustomerGroup(@SearchSpec Specification<CustomerGroup> customerGroup,
			Pageable pageable) {
		return customerGroupService.searchCustomerGroup(customerGroup, pageable);
	}

	/***
	 * this method using fetch all customerGroup
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/customergroup/dropdown")
	public List<CustomerGroup> fetchAllCustomerGroupList(@RequestHeader("x-tenant") String tenantId) {
		return customerGroupService.fetchAllCustomerGroupList(tenantId);
	}

}
