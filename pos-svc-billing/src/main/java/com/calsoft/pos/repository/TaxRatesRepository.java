package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.TaxRatesModel;

public interface TaxRatesRepository
		extends JpaRepository<TaxRatesModel, Long>, JpaSpecificationExecutor<TaxRatesModel> {
	Page<TaxRatesModel> findByTenantId(Pageable pageable, String tenantId);

	TaxRatesModel findByIdAndTenantId(long TaxRatesId, String tenantId);

	List<TaxRatesModel> findByTenantId(String tenantId);

}
