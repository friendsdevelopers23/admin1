package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.invoice.SalesFlatInvoice;

@Repository
public interface SalesFlatInvoiceJpaRepository extends JpaRepository<SalesFlatInvoice, Long> {

	
	List<SalesFlatInvoice> findByOrderIdAndTenantId(Long orderId,String tenantId);

}
