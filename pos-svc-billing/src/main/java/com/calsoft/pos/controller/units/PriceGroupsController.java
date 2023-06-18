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

import com.calsoft.pos.model.PriceGroups;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.PriceGroupsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class PriceGroupsController {
	@Autowired
	PriceGroupsService priceGroupsService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all priceGroups page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/pricegroups")
	public Page<PriceGroups> fetchAllPriceGroups(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return priceGroupsService.fetchAllPriceGroups(pageable, tenantId);
	}

	/***
	 * this method using fetch by single priceGroups
	 * 
	 * @param brandsId
	 * @return
	 */

	@GetMapping("/pricegroups/{id}")
	public PriceGroups findByPriceGroupsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return priceGroupsService.findByPriceGroups(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save priceGroups
	 * 
	 * 
	 * @param priceGroups
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/pricegroups")
	public ResponseWrapper savePriceGroups(@RequestBody final PriceGroups PriceGroups,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return priceGroupsService.savePriceGroups(PriceGroups, tenantId);
	}

	/***
	 * this method using priceGroups
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/pricegroups/filter")
	public Page<PriceGroups> searchPriceGroups(@SearchSpec Specification<PriceGroups> priceGroups, Pageable pageable) {
		return priceGroupsService.searchPriceGroups(priceGroups, pageable);
	}

	/***
	 * this method using fetch all priceGroups
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/pricegroups/dropdown")
	public List<PriceGroups> fetchAllPosSettingsList(@RequestHeader("x-tenant") String tenantId) {
		return priceGroupsService.fetchAllPriceGroupsList(tenantId);
	}

}
