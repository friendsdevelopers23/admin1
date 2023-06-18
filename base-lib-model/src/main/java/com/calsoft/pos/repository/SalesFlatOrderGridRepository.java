package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.order.SalesFlatOrderGrid;

@Repository
public interface SalesFlatOrderGridRepository
		extends JpaRepository<SalesFlatOrderGrid, Long>, JpaSpecificationExecutor<SalesFlatOrderGrid> {

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderGrid(u.entityId,u.status,count(*)) FROM SalesFlatOrderGrid u where u.tenantId=?1 group by u.status")
	List<SalesFlatOrderGrid> findAllSalesFlatOrderGrid(String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderGrid(u.entityId,u.status,count(*)) FROM SalesFlatOrderGrid u where u.status=?1 And u.tenantId=?2")
	List<SalesFlatOrderGrid> findBySalesFlatOrderGridStatus(String status, String tenantId);

	//
	Page<SalesFlatOrderGrid> findByTenantId(Pageable pageable, String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderGrid(u.entityId,u.status,count(*)) FROM SalesFlatOrderGrid u where u.tenantId=?1 And u.supplierId=?2 group by u.status")
	List<SalesFlatOrderGrid> findAllSalesFlatOrderGridWithVedor(String tenantId, String sid);

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderGrid(u.entityId,u.status,count(*)) FROM SalesFlatOrderGrid u where u.status=?1 And u.tenantId=?2 And u.supplierId=?3")
	List<SalesFlatOrderGrid> findBySalesFlatOrderGridStatusWithVedor(String status, String tenantId, String sid);

	Page<SalesFlatOrderGrid> findByTenantIdAndSupplierId(Pageable pageable, String tenantId, String sid);

//	@Query(value = "SELECT  new com.calsoft.pos.model.order.SalesFlatOrderGrid(a.incrementId,a.createdDate,a.billingName,a.shippingName,a.grandTotal,a.status,b.telephone) FROM SalesFlatOrderGrid a INNER JOIN SalesFlatOrderAddress b ON a.entityId=b.parentId where a.tenantId = ?1")
//	Page<SalesFlatOrderGrid> downloadReport(Pageable pageable, String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.order.SalesFlatOrderGrid(u.incrementId,u.status,u.billingName,u.shippingName,u.createdDate,u.grandTotal,CONCAT(b.street,',',b.city,',',b.region,',',b.postCode,b.telephone)) FROM SalesFlatOrderGrid u INNER JOIN SalesFlatOrderAddress b ON u.entityId=b.parentId  where u.tenantId=?1")
	Page<SalesFlatOrderGrid> downloadReport(Pageable pageable, String tenantId);

}
