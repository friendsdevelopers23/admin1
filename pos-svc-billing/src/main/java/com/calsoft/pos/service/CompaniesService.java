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
import com.calsoft.pos.model.CompaniesModel;
import com.calsoft.pos.repository.CompaniesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompaniesService {

	Logger logger = LoggerFactory.getLogger(CompaniesService.class);

	@Autowired
	CompaniesRepository companiesRepository;

	public Page<CompaniesModel> fetchAllCompanies(Pageable pageable, String tenantId) {

		return companiesRepository.findByTenantId(pageable, tenantId);

	}

	public CompaniesModel findByCompanies(String companiesId, String tenantId) {
		return companiesRepository.findByIdAndTenantId(Long.valueOf(companiesId), tenantId);
	}

	public ResponseWrapper saveCompanies(CompaniesModel companiesModel, String tenantId) {

		try {

			companiesModel.setTenantId(tenantId);
			companiesRepository.save(companiesModel);

			return new ResponseWrapper(companiesModel, ResponseCodes02.ADDCOMPANIES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDCOMPANIES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(companiesModel, ResponseCodes02.ADDCOMPANIES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDCOMPANIES_REC_FAILURE.getDescription());
		}
	}

	public Page<CompaniesModel> searchCompanies(Specification<CompaniesModel> specs, Pageable pageable) {

		return companiesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<CompaniesModel> fetchAllCompaniesList(String tenantId) {
		return companiesRepository.findByTenantId(tenantId);
	}
}
