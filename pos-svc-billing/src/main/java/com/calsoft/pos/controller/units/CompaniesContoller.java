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
import com.calsoft.pos.model.CompaniesModel;
import com.calsoft.pos.service.CompaniesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class CompaniesContoller {
	@Autowired
	CompaniesService companiesService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all Companies page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/companies")
	public Page<CompaniesModel> fetchAllCompanies(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return companiesService.fetchAllCompanies(pageable, tenantId);
	}

	/***
	 * this method using fetch by single Companies
	 * 
	 * @param CompaniesId
	 * @return
	 */

	@GetMapping("/companies/{id}")
	public CompaniesModel findCompaniesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return companiesService.findByCompanies(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save Companies
	 * 
	 * 
	 * @param Companies
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/companies")
	public ResponseWrapper saveCompanies(@RequestBody final CompaniesModel CompaniesModel,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return companiesService.saveCompanies(CompaniesModel, tenantId);
	}

	/***
	 * this method using Companies
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/companies/filter")
	public Page<CompaniesModel> searchCompanies(@SearchSpec Specification<CompaniesModel> companiesModel,
			Pageable pageable) {
		return companiesService.searchCompanies(companiesModel, pageable);
	}

	/***
	 * this method using fetch all Companies
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/companies/dropdown")
	public List<CompaniesModel> fetchAllCompaniesList(@RequestHeader("x-tenant") String tenantId) {
		return companiesService.fetchAllCompaniesList(tenantId);
	}

}
