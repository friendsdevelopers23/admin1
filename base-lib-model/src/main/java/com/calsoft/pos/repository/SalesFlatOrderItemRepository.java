package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.order.SalesFlatOrderItem;

@Repository
public interface SalesFlatOrderItemRepository
		extends JpaRepository<SalesFlatOrderItem, Long>, JpaSpecificationExecutor<SalesFlatOrderItem> {

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderItem(a.supplierName,sum(a.supplierCommisionIndividual),sum(a.supplierCommisionCancelled),sum(a.supplierCommissionRefunded),b.phone,b.address,b.city,b.country,a.supplierId) FROM SalesFlatOrderItem a INNER JOIN Supplier b ON b.id=a.supplierId where b.tenantId = ?1 group by a.supplierId")
	Page<SalesFlatOrderItem> fetchSupplierDataByCommision(String tenantId, Pageable pageable);

	@Query("SELECT new com.calsoft.pos.model.order.SalesFlatOrderItem(a.name,a.sku,sum(a.qtyCancelled),sum(a.qtyInvoiced),sum(a.qty),sum(a.qtyRefunded),sum(a.supplierCommissionAmount),sum(a.supplierCommisionCancelled),sum(a.supplierCommissionRefunded),a.productId) FROM SalesFlatOrderItem a WHERE a.supplierId=?1  group by a.productId ")
	Page<SalesFlatOrderItem> fetchBySupplier(String supplierId, Pageable pageable);

}
