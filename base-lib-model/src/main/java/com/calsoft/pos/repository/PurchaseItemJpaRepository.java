package com.calsoft.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.purchase.Purchase;
import com.calsoft.pos.model.purchase.PurchaseItems;

@Repository
public interface PurchaseItemJpaRepository
		extends JpaRepository<PurchaseItems, Long>, JpaSpecificationExecutor<Purchase> {

//	@Query(value="SELECT a FROM PurchaseItems a INNER JOIN Purchase b ON a.parentId=b.id where b.tenantId=?1 And a.expiry=?2 And a.quantityBalance>?3")
//	Page<PurchaseItems> findByTenantIdAndExpiryAndQuantityBalanceGreaterThan(String tenantId, Date toDate,
//			Double quantityBalance, Pageable pageable);

	@Query(value = "SELECT a FROM PurchaseItems a INNER JOIN Purchase b ON a.parentId=b.id where b.tenantId=?1 And a.expiry=?2 And a.quantityBalance>?3")
	List<PurchaseItems> findByTenantIdAndExpiryAndQuantityBalanceGreaterThan(String tenantId, Date toDate,
			Double quantityBalance);

	@Query(value = "SELECT * FROM cs_purchase_items a \r\n"
			+ "JOIN cs_purchase b ON a.parent_id=b.id \r\n"
			+ "where b.tenant_id=?1 And a.expiry=?2 And a.quantity_balance>?3 And b.status=?4 and a.stock_expiry_done=0", nativeQuery = true)
	List<PurchaseItems> fetchExpiry(String tenantId, Date toDate,
			Double quantityBalance, String status);
	
	@Query(value = "SELECT a FROM PurchaseItems a JOIN Purchase b ON a.parentId=b.id where b.tenantId=?1 And a.expiry=?2")
	List<PurchaseItems> fetchExpiry12(String tenantId,@Param("toDate") Date toDate);
	
	@Query(value = "SELECT a FROM PurchaseItems a INNER JOIN Purchase b ON a.parentId=b.id where b.tenantId=?1 And a.quantityBalance>=?2 And b.status=?3")
	List<PurchaseItems> fetchExpiry1234(String tenantId,
			Double quantityBalance, String status);

	@Query(value = "SELECT a FROM PurchaseItems a  where a.productId=?1 And a.quantityBalance>?2 order by a.parentId asc")
	List<PurchaseItems> fetchByProductId(Long productId, Double quantityBalance);

	List<PurchaseItems> findByPurchaseId(Long purchaseId);

	@Transactional
	@Modifying // to mark delete or update query
	@Query(value = "DELETE FROM PurchaseItems e WHERE e.purchaseId = ?1")
	void deleteByPurchaseId(long entityId);
	
	@Query(value = "SELECT  new com.calsoft.pos.model.purchase.PurchaseItems(a.purchaseId) FROM PurchaseItems a  where a.purchaseId IN(?1) and parentId=?2 ")
	List<PurchaseItems> fetchByPurchaseId(List<Long> purchaseId,Long parentId);

	
	
	

}
