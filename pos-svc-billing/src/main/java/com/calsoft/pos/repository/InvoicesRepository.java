package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Invoices;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long>, JpaSpecificationExecutor<Invoices> {
	Page<Invoices> findByTenantId(Pageable pageable, String tenantId);

	Invoices findByInvoiceIdAndTenantId(long InvoicesId, String tenantId);

	List<Invoices> findByTenantId(String tenantId);
}
