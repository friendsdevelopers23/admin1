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
import com.calsoft.pos.model.Kitchen_MasterModel;
import com.calsoft.pos.service.Kitchen_MasterService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class Kitchen_MasterController {
	@Autowired
	Kitchen_MasterService kitchen_MasterService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all kitchen_Master page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/kitchen_Master")
	public Page<Kitchen_MasterModel> fetchAllKitchen(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return kitchen_MasterService.fetchAllKitchen(pageable, tenantId);
	}

	/***
	 * this method using fetch by single kitchen_Master
	 * 
	 * @param KitchenId
	 * @return
	 */

	@GetMapping("/kitchen_Master/{id}")
	public Kitchen_MasterModel findKitchenId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return kitchen_MasterService.findByKitchen(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save kitchen_Master
	 * 
	 * 
	 * @param kitchen_Master
	 * @return
	 * @throws Parsekitchen_Master
	 */
	@PostMapping("/kitchen_Master")
	public ResponseWrapper saveKitchen(@RequestBody final Kitchen_MasterModel Kitchen_MasterModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return kitchen_MasterService.saveKitchen(Kitchen_MasterModel, tenantId);
	}

	/***
	 * this method using kitchen_Master
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/kitchen_Master/filter")
	public Page<Kitchen_MasterModel> searchKitchen(@SearchSpec Specification<Kitchen_MasterModel> kitchen_MasterModel,
			Pageable pageable) {
		return kitchen_MasterService.searchKitchen(kitchen_MasterModel, pageable);
	}

	/***
	 * this method using fetch all kitchen_Master
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/kitchen_Master/dropdown")
	public List<Kitchen_MasterModel> fetchAllKitchenList(@RequestHeader("x-tenant") String tenantId) {
		return kitchen_MasterService.fetchAllKitchenList(tenantId);
	}
}
