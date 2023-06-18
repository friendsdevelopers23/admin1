package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.Products;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.ProductsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsService {
	Logger logger = LoggerFactory.getLogger(ProductsService.class);
	@Autowired
	ProductsRepository productsRepository;

	public Page<Products> fetchAllProducts(Pageable pageable, String tenantId) {

		return productsRepository.findByTenantId(pageable, tenantId);

	}

	public Products findByProducts(String productsId, String tenantId) {
		return productsRepository.findByIdAndTenantId(Long.valueOf(productsId), tenantId);
	}

	public ResponseWrapper saveProducts(Products products, String tenantId) {

		try {

			products.setTenantId(tenantId);
			productsRepository.save(products);

			return new ResponseWrapper(products, ResponseCodes02.ADDPRODUCTS_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDPRODUCTS_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(products, ResponseCodes02.ADDPRODUCTS_REC_FAILURE.getCode(),
					ResponseCodes02.ADDPRODUCTS_REC_FAILURE.getDescription());
		}
	}

	public Page<Products> searchProducts(Specification<Products> specs, Pageable pageable) {

		return productsRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Products> fetchAllProductsList(String tenantId) {
		return productsRepository.findByTenantId(tenantId);
	}
}
