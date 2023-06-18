
package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.cart.SalesFlatQuote;

@Repository
public interface SalesFlatQuoteJpaRepository
		extends JpaRepository<SalesFlatQuote, Long>, JpaSpecificationExecutor<SalesFlatQuote> {

	public SalesFlatQuote findByEntityId(long entityId);

	public SalesFlatQuote findByCustomerIdAndIsActive(long customerId, long isActive);

	public SalesFlatQuote findByEntityIdAndIsActive(long entityId, long isActive);

	public SalesFlatQuote findByCustomerIdAndEntityId(int customerId, long entityId);

	public Page<SalesFlatQuote> findByIsActive(long isActive, Pageable pageable);

	///

	public SalesFlatQuote findByEntityIdAndTenantIdAndIsActive(Long entityId, String tenantId,long isActive);

	@Query("select c from SalesFlatQuote c where c.customerId=?1 and c.isActive=?2 and (c.tenantId=?3 or c.tenantId=?4)")
	public SalesFlatQuote fetchAllCustomerIdAndIsActiveAndTenantIdOrTenantId(long customerId, long isActive,
			String tenantId, String primaryTenant);
	
	@Query("select c from SalesFlatQuote c where c.customerId=?1 and c.isActive=?2 and (c.tenantId=?3 or c.tenantId=?4)")
	public List<SalesFlatQuote> fetchAllQuoteData(long customerId, long isActive,
			String tenantId, String primaryTenant);

	public SalesFlatQuote findByCustomerIdAndEntityIdAndTenantId(Long customerId, Long isActive, String tenantId);

	public SalesFlatQuote findByEntityIdAndIsActiveAndTenantId(long entityId, long isActive, String tenantId);

	public List<SalesFlatQuote> findByTenantId(String tenantId);

	public Page<SalesFlatQuote> findByIsActiveAndTenantId(long isActive, Pageable pageable, String tenantId);

	public SalesFlatQuote findByCustomerIdAndIsActiveAndTenantId(long customerId, long isActive, String tenantId);

	// pos
	@Query("select c from SalesFlatQuote c where c.billerId=?1 and c.isVirtual=?2 and (c.tenantId=?3 or c.tenantId=?4)")
	public SalesFlatQuote fetchAllBillerIdAndIsVirtualAndTenantIdOrTenantId(Long billerId, Long isVirtual,
			String tenantId, String systemDefaultTenantId);

	public SalesFlatQuote findByEntityIdAndIsVirtualAndTenantId(Long entityId, Long isVirtual, String tenantId);

	public SalesFlatQuote findByBillerIdAndEntityIdAndTenantId(long billerId, Long entityId, String tenantId);

	@Query("select c from SalesFlatQuote c where c.billerId=?1 and c.isSuspended=?2 and (c.tenantId=?3 or c.tenantId=?4)")
	public Page<SalesFlatQuote> fetchAllBillerIdAndIsSuspendedAndTenantIdOrTenantId(Long billerId, int isSuspended, String tenantId,
			String systemDefaultTenantId, Pageable pageable);
	
	@Query("select c from SalesFlatQuote c where c.billerId=?1 and c.isSuspended=?2 and (c.tenantId=?3 or c.tenantId=?4)")
	public SalesFlatQuote fetchAllBillerIdAndIsSuspendeddAndTenantIdOrTenantId(Long billerId, int isSuspended,
			String tenantId, String systemDefaultTenantId);
	
	public  List<SalesFlatQuote> findByTenantIdAndEntityId(String tenantId,long entityId);

	public SalesFlatQuote findByEntityIdAndTenantId(Long valueOf, String tenantId);

	


}
