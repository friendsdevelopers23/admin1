package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.invoice.SalesFlatInvoiceGrid;

@Repository
public interface SalesFlatInvoiceGridRepository
		extends JpaRepository<SalesFlatInvoiceGrid, Long>, JpaSpecificationExecutor<SalesFlatInvoiceGrid> {

	Page<SalesFlatInvoiceGrid> findByTenantId(Pageable pageable, String tenantId);

	Page<SalesFlatInvoiceGrid> findByTenantIdAndSupplierId(Pageable pageable, String tenantId, String sid);

}
