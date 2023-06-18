package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.ApproveTransactinos;

public interface ApproveTransactinosRepository
		extends JpaRepository<ApproveTransactinos, Long>, JpaSpecificationExecutor<ApproveTransactinos> {
	Page<ApproveTransactinos> findByTenantId(Pageable pageable, String tenantId);

	ApproveTransactinos findByIdAndTenantId(long ApproveTransactinosId, String tenantId);

	List<ApproveTransactinos> findByTenantId(String tenantId);

}
