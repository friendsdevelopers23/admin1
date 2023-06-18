
package com.calsoft.pos.controller.units;

import java.text.ParseException;

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
import com.calsoft.pos.model.Units;
import com.calsoft.pos.service.UnitsService;
import com.calsoft.springsearch.anotation.SearchSpec;

@RequestMapping("/api")
@RestController
public class UnitsController {

	@Autowired
	public UnitsService unitsService;

	/***
	 * this method using country page filter columns name
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping(value = "/units/filter")
	public Page<Units> searchUnits(@SearchSpec Specification<Units> specs, Pageable pageable) {
		return unitsService.searchUnitsData(specs, pageable);
	}

	@PostMapping(value = "/units")
	public ResponseWrapper saveUnits(@RequestBody final Units units,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {

		return unitsService.saveUnits(units, tenantId);

	}

	@GetMapping(value = "/units")
	public Page<Units> fetchAllUnits(Pageable pageable,
			@RequestHeader("x-tenant") String tenantId) {
		return unitsService.getAllUnitsData(pageable, tenantId);
	}

	/***
	 * this method using fetch by Units based on single keyId
	 * 
	 * @param keyId
	 * @return
	 */

	@GetMapping("/units/{id}")
	public Units findByKeyId(@PathVariable(value = "id") String id,@RequestHeader("x-tenant") String tenantId) {

		return unitsService.fetchById(Integer.parseInt(id),tenantId);
	}
	
	


}
