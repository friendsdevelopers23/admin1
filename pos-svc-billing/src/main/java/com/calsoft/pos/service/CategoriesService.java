package com.calsoft.pos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.model.Categories;
import com.calsoft.pos.repository.CategoriesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriesService {
	Logger logger = LoggerFactory.getLogger(CategoriesService.class);
	@Autowired
	CategoriesRepository categoriesRepository;

	public Page<Categories> fetchAllCategories(Pageable pageable, String tenantId) {

		return categoriesRepository.findByTenantId(pageable, tenantId);

	}

	public Categories findByCategories(String categoriesId, String tenantId) {
		return categoriesRepository.findByIdAndTenantId(Long.valueOf(categoriesId), tenantId);
	}

	public ResponseWrapper saveCategories(Categories categories, String tenantId) {

		try {

			categories.setTenantId(tenantId);
			categoriesRepository.save(categories);

			return new ResponseWrapper(categories, ResponseCodes02.ADDCATEGORIES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDCATEGORIES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(categories, ResponseCodes02.ADDCATEGORIES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDCATEGORIES_REC_FAILURE.getDescription());
		}
	}

	public Page<Categories> searchCategories(Specification<Categories> specs, Pageable pageable) {

		return categoriesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Categories> fetchAllCategoriesList(String tenantId) {
		return categoriesRepository.findByTenantId(tenantId);
	}

}
