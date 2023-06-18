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
import com.calsoft.pos.model.Promos;
import com.calsoft.pos.service.PromosService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class PromosController {
	@Autowired
	PromosService promosService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all promos page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/promos")
	public Page<Promos> fetchAllPromos(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return promosService.fetchAllPromos(pageable, tenantId);
	}

	/***
	 * this method using fetch by single promos
	 * 
	 * @param promosId
	 * @return
	 */

	@GetMapping("/promos/{id}")
	public Promos findByPromosId(@PathVariable(value = "id") String id, @RequestHeader("x-tenant") String tenantId) {
		return promosService.findByPromos(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save promos
	 * 
	 * 
	 * @param promos
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/promos")
	public ResponseWrapper savePromos(@RequestBody final Promos Promos, @RequestHeader("x-tenant") String tenantId)
			throws ParseException {
		return promosService.savePromos(Promos, tenantId);
	}

	/***
	 * this method using promos
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/promos/filter")
	public Page<Promos> searchPromos(@SearchSpec Specification<Promos> promos, Pageable pageable) {
		return promosService.searchPromos(promos, pageable);
	}

	/***
	 * this method using fetch all posSettings
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/promos/dropdown")
	public List<Promos> fetchAllPromosList(@RequestHeader("x-tenant") String tenantId) {
		return promosService.fetchAllPromosList(tenantId);
	}

}
