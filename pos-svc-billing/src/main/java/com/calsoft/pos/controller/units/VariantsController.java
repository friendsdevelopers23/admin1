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
import com.calsoft.pos.model.Variants;
import com.calsoft.pos.service.VariantsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class VariantsController {
	@Autowired
	VariantsService variantsService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all variants page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/variants")
	public Page<Variants> fetchAllVariants(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return variantsService.fetchAllVariants(pageable, tenantId);

	}

	/***
	 * this method using fetch by single variants
	 * 
	 * @param VariantsId
	 * @return
	 */

	@GetMapping("/variants/{id}")
	public Variants findByvariantsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return variantsService.findByVariants(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save variants
	 * 
	 * 
	 * @param Variants
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/variants")
	public ResponseWrapper saveVarients(@RequestBody final Variants Variants,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return variantsService.saveVariants(Variants, tenantId);
	}

	/***
	 * this method using variants
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/variants/filter")
	public Page<Variants> searchVariants(@SearchSpec Specification<Variants> variants, Pageable pageable) {
		return variantsService.searchVariants(variants, pageable);
	}

	/***
	 * this method using fetch all variants
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/variants/dropdown")
	public List<Variants> fetchAllVariantsList(@RequestHeader("x-tenant") String tenantId) {
		return variantsService.fetchAllVariantsList(tenantId);
	}

}
