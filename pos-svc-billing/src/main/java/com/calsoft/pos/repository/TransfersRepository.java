package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Transfers;

@Repository

public interface TransfersRepository extends JpaRepository<Transfers, Long>, JpaSpecificationExecutor<Transfers> {
	Page<Transfers> findByTenantId(Pageable pageable, String tenantId);

	Transfers findByTransferIdAndTenantId(long TransferId, String tenantId);

	List<Transfers> findByTenantId(String tenantId);

}
