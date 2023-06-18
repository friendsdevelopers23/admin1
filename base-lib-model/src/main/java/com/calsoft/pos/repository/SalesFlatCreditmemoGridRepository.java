package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.creditmemo.SalesFlatCreditmemoGrid;


public interface SalesFlatCreditmemoGridRepository
		extends JpaRepository<SalesFlatCreditmemoGrid, Long>, JpaSpecificationExecutor<SalesFlatCreditmemoGrid> {

	Page<SalesFlatCreditmemoGrid> findByTenantId(Pageable pageable, String tenantId);

	Page<SalesFlatCreditmemoGrid> findByTenantIdAndSupplierId(Pageable pageable, String tenantId, String sid);

}
