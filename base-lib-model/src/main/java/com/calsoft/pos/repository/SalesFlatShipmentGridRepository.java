package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.shipment.SalesFlatShipmentGrid;

@Repository
public interface SalesFlatShipmentGridRepository extends JpaRepository<SalesFlatShipmentGrid, Long>,JpaSpecificationExecutor<SalesFlatShipmentGrid> {

	Page<SalesFlatShipmentGrid> findByTenantId(Pageable pageable, String tenantId);

	Page<SalesFlatShipmentGrid> findByTenantIdAndSupplierId(Pageable pageable, String tenantId, String sid);

}
