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

import com.calsoft.pos.model.Products;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.service.ProductsService;
import com.calsoft.springsearch.anotation.SearchSpec;
import com.calsoft.utils.UserUtils;

@RestController
@RequestMapping("/api")
public class ProductsController {
	@Autowired
	ProductsService productsService;
	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using fetch all products page show grid
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/products")
	public Page<Products> fetchAllProducts(Pageable pageable, @RequestHeader("x-tenant") String tenantId) {
		return productsService.fetchAllProducts(pageable, tenantId);
	}

	/***
	 * this method using fetch by single products
	 * 
	 * @param productsId
	 * @return
	 */

	@GetMapping("/products/{id}")
	public Products findByProductsId(@PathVariable(value = "id") String id,
			@RequestHeader("x-tenant") String tenantId) {
		return productsService.findByProducts(id, tenantId);
	}

	/***
	 * 
	 * 
	 * this method using save products
	 * 
	 * 
	 * @param products
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/products")
	public ResponseWrapper saveProducts(@RequestBody final Products Products,
			@RequestHeader("x-tenant") String tenantId) throws ParseException {
		return productsService.saveProducts(Products, tenantId);
	}

	/***
	 * this method using products
	 * 
	 * 
	 * @param specs
	 * @param pageable
	 * @return
	 */
	@GetMapping("/products/filter")
	public Page<Products> searchProducts(@SearchSpec Specification<Products> products, Pageable pageable) {
		return productsService.searchProducts(products, pageable);
	}

	/***
	 * this method using fetch all products
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/products/dropdown")
	public List<Products> fetchAllProductsList(@RequestHeader("x-tenant") String tenantId) {
		return productsService.fetchAllProductsList(tenantId);
	}

}
