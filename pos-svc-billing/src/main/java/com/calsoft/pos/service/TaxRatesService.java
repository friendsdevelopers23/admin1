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
import com.calsoft.pos.model.TaxRatesModel;
import com.calsoft.pos.repository.TaxRatesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaxRatesService {

	Logger logger = LoggerFactory.getLogger(TaxRatesService.class);

	@Autowired
	TaxRatesRepository taxRatesRepository;

	public Page<TaxRatesModel> fetchAllTaxRates(Pageable pageable, String tenantId) {

		return taxRatesRepository.findByTenantId(pageable, tenantId);

	}

	public TaxRatesModel findByTaxRates(String taxRatesId, String tenantId) {
		return taxRatesRepository.findByIdAndTenantId(Long.valueOf(taxRatesId), tenantId);
	}

	public ResponseWrapper saveTaxRates(TaxRatesModel taxRatesModel, String tenantId) {

		try {

			taxRatesModel.setTenantId(tenantId);
			taxRatesRepository.save(taxRatesModel);

			return new ResponseWrapper(taxRatesModel, ResponseCodes02.ADDTAX_RATES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDTAX_RATES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(taxRatesModel, ResponseCodes02.ADDTAX_RATES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDTAX_RATES_REC_FAILURE.getDescription());
		}
	}

	public Page<TaxRatesModel> searchTaxRates(Specification<TaxRatesModel> specs, Pageable pageable) {

		return taxRatesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<TaxRatesModel> fetchAllTaxRatesList(String tenantId) {
		return taxRatesRepository.findByTenantId(tenantId);
	}

}
