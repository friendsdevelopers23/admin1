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
import com.calsoft.pos.model.Currencies;
import com.calsoft.pos.repository.CurrenciesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrenciesService {
	Logger logger = LoggerFactory.getLogger(CurrenciesService.class);

	@Autowired
	CurrenciesRepository currenciesRepository;

	public Page<Currencies> fetchAllCurrencies(Pageable pageable, String tenantId) {

		return currenciesRepository.findByTenantId(pageable, tenantId);

	}

	public Currencies findByCurrencies(String currenciesId, String tenantId) {
		return currenciesRepository.findByIdAndTenantId(Long.valueOf(currenciesId), tenantId);
	}

	public ResponseWrapper saveCurrencies(Currencies currencies, String tenantId) {

		try {

			currencies.setTenantId(tenantId);
			currenciesRepository.save(currencies);

			return new ResponseWrapper(currencies, ResponseCodes02.ADDCURRENCIES_REC_SUCCESS.getCode(),
					ResponseCodes02.ADDCURRENCIES_REC_SUCCESS.getDescription());

		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);

			return new ResponseWrapper(currencies, ResponseCodes02.ADDCURRENCIES_REC_FAILURE.getCode(),
					ResponseCodes02.ADDCURRENCIES_REC_FAILURE.getDescription());
		}
	}

	public Page<Currencies> searchCurrencies(Specification<Currencies> specs, Pageable pageable) {

		return currenciesRepository.findAll(Specification.where(specs), pageable);
	}

	public List<Currencies> fetchAllCurrenciesList(String tenantId) {
		return currenciesRepository.findByTenantId(tenantId);
	}
}
