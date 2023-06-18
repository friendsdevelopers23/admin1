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
import com.calsoft.pos.model.Currencies;
import com.calsoft.pos.service.CurrenciesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class CurrenciesController {
	@Autowired
	CurrenciesService currenciesService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all currencies page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/currencies")
	public Page<Currencies> fetchAllCurrencies(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return currenciesService.fetchAllCurrencies(pageable, tenantId);
	}

	/***
	 * this method using fetch by single currencies
	 * 
	 * @param currenciesId
	 * @return
	 */

	@GetMapping("/currencies/{id}")
	public Currencies findByCurrenciesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return currenciesService.findByCurrencies(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save currencies
	 * 
	 * 
	 * @param currencies
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/currencies")
	public ResponseWrapper saveCurrencies(@RequestBody final Currencies Currencies,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return currenciesService.saveCurrencies(Currencies, tenantId);
	}

	/***
	 * this method using currencies
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/currencies/filter")
	public Page<Currencies> searchCurrencies(@SearchSpec Specification<Currencies> currencies, Pageable pageable) {
		return currenciesService.searchCurrencies(currencies, pageable);
	}

	/***
	 * this method using fetch all currencies
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/currencies/dropdown")
	public List<Currencies> fetchAllCurrencies(@RequestHeader("x-tenant") String tenantId) {
		return currenciesService.fetchAllCurrenciesList(tenantId);
	}

}
