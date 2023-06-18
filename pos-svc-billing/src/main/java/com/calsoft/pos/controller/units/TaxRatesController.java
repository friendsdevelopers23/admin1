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
import com.calsoft.pos.model.TaxRatesModel;
import com.calsoft.pos.service.TaxRatesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class TaxRatesController {
	@Autowired
	TaxRatesService taxRatesService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all TaxRates page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/taxrates")
	public Page<TaxRatesModel> fetchAllTaxRates(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return taxRatesService.fetchAllTaxRates(pageable, tenantId);
	}

	/***
	 * this method using fetch by single TaxRate
	 * 
	 * @param TaxRatesId
	 * @return
	 */

	@GetMapping("/taxrates/{id}")
	public TaxRatesModel findBytaxRatesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return taxRatesService.findByTaxRates(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save TaxRate
	 * 
	 * 
	 * @param Taxrate
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/taxrates")
	public ResponseWrapper saveTaxRates(@RequestBody final TaxRatesModel TaxRatesModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return taxRatesService.saveTaxRates(TaxRatesModel, tenantId);
	}

	/***
	 * this method using TaxRate
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/taxrates/filter")
	public Page<TaxRatesModel> searchTaxRates(@SearchSpec Specification<TaxRatesModel> taxRatesModel,
			Pageable pageable) {
		return taxRatesService.searchTaxRates(taxRatesModel, pageable);
	}

	/***
	 * this method using fetch all TaxRate
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/taxrates/dropdown")
	public List<TaxRatesModel> fetchAllTaxRatesList(@RequestHeader("x-tenant") String tenantId) {
		return taxRatesService.fetchAllTaxRatesList(tenantId);
	}

}
