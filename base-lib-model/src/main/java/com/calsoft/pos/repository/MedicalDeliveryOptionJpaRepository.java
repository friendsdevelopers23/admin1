package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.medical.MedicalDeliveryOption;

public interface MedicalDeliveryOptionJpaRepository
		extends JpaRepository<MedicalDeliveryOption, Long>, JpaSpecificationExecutor<MedicalDeliveryOption> {

	MedicalDeliveryOption findByIdAndTenantId(long supplierId, String tenantId);

	Page<MedicalDeliveryOption> findByTenantId(Pageable pageable, String tenantId);

	List<MedicalDeliveryOption> findByTenantId(String tenantId);

	MedicalDeliveryOption findByTypeAndTenantId(int type, String tenantId);

	List<MedicalDeliveryOption> findByIsActiveAndTenantId(int i, String tenantId);

}