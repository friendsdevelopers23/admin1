package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.PaymentMode;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long>, JpaSpecificationExecutor<PaymentMode> {

	Page<PaymentMode> findByTenantId(Pageable pageable, String tenantId);

	PaymentMode findByTenantIdAndId(String tenantId, Long id);

	Page<PaymentMode> findByTenantIdAndIsActive(Pageable pageable, String tenantId, int i);

	PaymentMode findByTenantIdAndName(String tenantId, String name);

	PaymentMode findByTenantIdAndType(String tenantId, String type);
	
	List<PaymentMode> findByTenantIdAndIsActiveOrderByIdDesc(String tenantId, int i);
	
	List<PaymentMode> findByTenantId(String tenantId);
	
	

}
