package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.taxclass.TaxCalculationRate;


public interface TaxRateJpaRepository extends JpaRepository<TaxCalculationRate, Long>,JpaSpecificationExecutor<TaxCalculationRate> {

	TaxCalculationRate findByTaxCalculationRateId(long taxCalculationRateId);

	//
	Page<TaxCalculationRate> findByTenantId(Pageable pageable, String tenantId);

	TaxCalculationRate findByTaxCalculationRateIdAndTenantId(Long taxCalculationRateId, String tenantId);

	List<TaxCalculationRate> findByTenantId(String tenantId);
	
    @Query(value="SELECT new com.calsoft.pos.model.taxclass.TaxCalculationRate(a.rate,a.cgst,a.sgst,a.igst,c.calculateSubtotal) FROM TaxCalculationRate a INNER JOIN TaxCalculation b ON a.taxCalculationRateId=b.taxCalculationRateId INNER JOIN TaxCalculationRule c ON b.taxCalculationRuleId=c.taxCalculationRuleId where b.customerTaxClassId =?1 Or b.productTaxClassId=?1 ORDER BY a.rate DESC")
    Page<TaxCalculationRate> findTaxPercent(long classId,Pageable pageable);

}