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
import com.calsoft.pos.model.BrandsModel;
import com.calsoft.pos.service.BrandsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class BrandsController {
	@Autowired
	BrandsService brandsService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all Brands page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/brands")
	public Page<BrandsModel> fetchAllBrands(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return brandsService.fetchAllBrands(pageable, tenantId);
	}

	/***
	 * this method using fetch by single Brands
	 * 
	 * @param brandsId
	 * @return
	 */

	@GetMapping("/brands/{id}")
	public BrandsModel findBybrandsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return brandsService.findByBrands(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save Brands
	 * 
	 * 
	 * @param Brands
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/brands")
	public ResponseWrapper saveBrandas(@RequestBody final BrandsModel BrandsModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return brandsService.saveBrands(BrandsModel, tenantId);
	}

	/***
	 * this method using Brands
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/brands/filter")
	public Page<BrandsModel> searchBrands(@SearchSpec Specification<BrandsModel> brandsModel, Pageable pageable) {
		return brandsService.searchBrands(brandsModel, pageable);
	}

	/***
	 * this method using fetch all Brands
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/brands/dropdown")
	public List<BrandsModel> fetchAllBrandsList(@RequestHeader("x-tenant") String tenantId) {
		return brandsService.fetchAllBrandsList(tenantId);
	}

}
