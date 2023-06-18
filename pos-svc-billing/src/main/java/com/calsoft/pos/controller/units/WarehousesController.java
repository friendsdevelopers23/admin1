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
import com.calsoft.pos.model.WarehousesModel;
import com.calsoft.pos.service.WarehousesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RequestMapping("/api")
@RestController
public class WarehousesController {

	@Autowired
	WarehousesService warehousesService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all warehouses page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/warehouses")
	public Page<WarehousesModel> fetchAllWarehouses(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return warehousesService.fetchAllWarehouses(pageable, tenantId);
	}

	/***
	 * this method using fetch by single warehouses
	 * 
	 * @param DeliveryPeopleId
	 * @return
	 */

	@GetMapping("/warehouses/{id}")
	public WarehousesModel findBywarehousesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return warehousesService.findByWarehouses(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save warehouses
	 * 
	 * 
	 * @param DeliveryPeople
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/warehouses")
	public ResponseWrapper saveWarehouses(@RequestBody final WarehousesModel WarehousesModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return warehousesService.saveWarehouses(WarehousesModel, tenantId);
	}

	/***
	 * this method using warehouses
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/warehouses/filter")
	public Page<WarehousesModel> searchWarehouses(@SearchSpec Specification<WarehousesModel> warehousesModel,
			Pageable pageable) {
		return warehousesService.searchWarehouses(warehousesModel, pageable);
	}

	/***
	 * this method using fetch all warehouses
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/warehouses/dropdown")
	public List<WarehousesModel> fetchAllWarehousesList(@RequestHeader("x-tenant") String tenantId) {
		return warehousesService.fetchAllWarehousesList(tenantId);
	}

}
