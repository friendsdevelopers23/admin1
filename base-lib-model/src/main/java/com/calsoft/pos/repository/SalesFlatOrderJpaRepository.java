package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.order.SalesFlatOrder;

@Repository
public interface SalesFlatOrderJpaRepository
		extends JpaRepository<SalesFlatOrder, Long>, JpaSpecificationExecutor<SalesFlatOrder> {

	SalesFlatOrder findByEntityId(long entityId);

	SalesFlatOrder findByEntityIdAndCustomerId(long entityId, int customerId);

	/***
	 * order
	 * 
	 * @param customerFirstname
	 * @return
	 */

	List<SalesFlatOrder> findBycustomerFirstname(String customerFirstname);

	Page<SalesFlatOrder> findByCustomerId(int customerId, Pageable pageable);

	
	///
	Page<SalesFlatOrder> findByTenantId(Pageable pageable, String tenantId);

	SalesFlatOrder findByEntityIdAndCustomerIdAndTenantId(Long entityId, Integer customerId, String tenantId);

	Page<SalesFlatOrder> findByCustomerIdAndTenantId(Integer customerId, Pageable pageable, String tenantId);

	SalesFlatOrder findByEntityIdAndTenantId(Long entityId, String tenantId);
	
	@Query("select count(*) from SalesFlatOrder c where c.tenantId=?1")
	public Integer findOrderCount(String tenantId);
	
	@Query("select new com.calsoft.pos.model.order.SalesFlatOrder(SUM(u.grandTotal),SUM(u.totalInvoiced),count(*),SUM(u.grandTotal)/count(*),SUM(itemsQty)) FROM SalesFlatOrder u where u.tenantId=?1")
	public SalesFlatOrder getDataForDashBoard(String tenantId);
	
	@Query("SELECT new com.calsoft.pos.model.order.SalesFlatOrder(SUM(u.grandTotal),SUM(u.totalInvoiced),count(*),SUM(u.grandTotal)/count(*),SUM(itemsQty)) FROM SalesFlatOrder u  where u.tenantId=?1 and u.salesFlatOrderGrid.supplierId=?2")
	public SalesFlatOrder getDataForDashBoardBasedOnVendor(String tenantId,String sid);

}
