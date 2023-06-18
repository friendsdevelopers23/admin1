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

import com.calsoft.pos.model.Categories;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.CategoriesService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class CategoriesController {
	@Autowired
	CategoriesService categoriesService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all categories page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/categories")
	public Page<Categories> fetchAllCategories(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return categoriesService.fetchAllCategories(pageable, tenantId);
	}

	/***
	 * this method using fetch by single categories
	 * 
	 * @param CategoriesId
	 * @return
	 */

	@GetMapping("/categories/{id}")
	public Categories findCategoriesId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return categoriesService.findByCategories(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save categories
	 * 
	 * 
	 * @param Categories
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/categories")
	public ResponseWrapper saveCategories(@RequestBody final Categories Categories,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return categoriesService.saveCategories(Categories, tenantId);
	}

	/***
	 * this method using categories
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/categories/filter")
	public Page<Categories> searchCompanies(@SearchSpec Specification<Categories> categories, Pageable pageable) {
		return categoriesService.searchCategories(categories, pageable);
	}

	/***
	 * this method using fetch all Companies
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/categories/dropdown")
	public List<Categories> fetchAllCategoriesList(@RequestHeader("x-tenant") String tenantId) {
		return categoriesService.fetchAllCategoriesList(tenantId);
	}
}
