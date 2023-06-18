package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.currency.DirectoryCurrencyRate;

@Repository
public interface DirectoryCurrencyRateJpaRepository
		extends JpaRepository<DirectoryCurrencyRate, Long>, JpaSpecificationExecutor<DirectoryCurrencyRate> {

	DirectoryCurrencyRate findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);

	///
	Page<DirectoryCurrencyRate> findByTenantId(Pageable pageable, String tenantId);

	DirectoryCurrencyRate findByCurrencyFromAndCurrencyToAndTenantId(String string, String currencyFrom,
			String tenantId);

	List<DirectoryCurrencyRate> findByTenantId(String tenantId);

}
