package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CreditDebitEntry;

@Repository
public interface CreditDebitEntryRepository
		extends JpaRepository<CreditDebitEntry, Long>, JpaSpecificationExecutor<CreditDebitEntry> {

	Page<CreditDebitEntry> findByTenantId(Pageable pageable, String tenantId);

	CreditDebitEntry findByIdAndTenantId(long creditDebitEntryId, String tenantId);

	List<CreditDebitEntry> findByTenantId(String tenantId);
}
