
package com.calsoft.pos.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.cart.SalesFlatQuoteItem;
import com.calsoft.pos.model.productindex.ProductIDX2;

@Repository
public interface SalesFlatQuoteItemJpaRepository
		extends JpaRepository<SalesFlatQuoteItem, Integer>, JpaSpecificationExecutor<SalesFlatQuoteItem> {

	SalesFlatQuoteItem findByItemId(long itemId);

	SalesFlatQuoteItem findByProductIdAndSalesFlatQuoteEntityId(long productId, long salesFlatQuote_entityId);

	List<SalesFlatQuoteItem> findByProductIdAndSalesFlatQuoteIsActive(long productId, long isActive);

	@Query(value = "SELECT count(*)\n" + "	 		FROM cs_sales_flat_quote_item\n" + "	 		where \n"
			+ "	 		 quote_id IN (\n" + "	 		    SELECT entity_id\n"
			+ "	 		    FROM cs_sales_flat_quote\n" + "	 		    where\n" + "	 		    customer_id=?1\n"
			+ "                and\n" + "                tenant_id=?2\n" + "	 		and\n"
			+ "	 		is_active=1\n" + "	 		)", nativeQuery = true)
	public Long cartCount(long customerId, String tenantId);
	
	@Query(value = "SELECT count(*)\n" + "	 		FROM cs_sales_flat_quote_item\n" + "	 		where \n"
			+ "	 		 quote_id IN (\n" + "	 		    SELECT entity_id\n"
			+ "	 		    FROM cs_sales_flat_quote\n" + "	 		    where\n" + "	 		    quote_id=?1\n"
			+ "                and\n" + "                tenant_id=?2\n" + "	 		and\n"
			+ "	 		is_active=1\n" + "	 		)", nativeQuery = true)
	public Long cartCountEntityId(long customerId, String tenantId);

	@Query("SELECT count(u) FROM SalesFlatQuoteItem  u WHERE u.productId = ?1 and u.quoteId IN(SELECT v FROM SalesFlatQuote v WHERE v.isActive = 1) ")
	Long findByActiveProductInCart(long productId);

	@Query("SELECT count(u) FROM SalesFlatOrderItem  u WHERE u.productId = ?1 ")
	Long findByBasedOnProductOrder(long productId);

	@Query("SELECT  DISTINCT u FROM SalesFlatQuoteItem u WHERE  u.quoteId IN(SELECT v FROM SalesFlatQuote v WHERE v.isActive = 1 and v.tenantId=?1) ")
	Page<SalesFlatQuoteItem> findByDistintProductInCart(Pageable pageable, String tenantId);
	
	@Query(value="SELECT new com.calsoft.pos.model.cart.SalesFlatQuoteItem(a.productId,SUM(a.qty),SUM(a.stockInHand)) FROM SalesFlatQuoteItem a INNER JOIN SalesFlatQuote b ON a.quoteId=b.entityId where b.isActive =?1 And b.customerId=?2  group by a.productId ")
	List<SalesFlatQuoteItem>  findProductCountAddedInCart(long isActive,Long customerId);
	
	
	@Query(value="SELECT new com.calsoft.pos.model.cart.SalesFlatQuoteItem(a.productId,SUM(a.qty),SUM(a.stockInHand)) FROM SalesFlatQuoteItem a INNER JOIN SalesFlatQuote b ON a.quoteId=b.entityId where b.isActive =?1 And b.entityId=?2  group by a.productId ")
	List<SalesFlatQuoteItem>  findProductCountAddedInCartWithUserAuthenticated(long isActive,Long customerId);
	
	@Transactional(rollbackFor=Exception.class)
	 @Modifying      // to mark delete or update query
	 @Query(value = "DELETE FROM SalesFlatQuoteItem e WHERE e.itemId = ?1")   
	void deleteByItemId(Long item);
	
	@Query(value = "SELECT count(*) FROM  cs_sales_flat_quote_item a left JOIN cs_sales_flat_quote b ON a.quote_id=b.entity_id WHERE a.prescription_required=1 and b.is_active=1 and b.customer_id=?1 and (b.tenant_id=?2 or b.tenant_id=?3)",nativeQuery = true)
	public Long fetchAllPrescriptionItem(long customerId,String tenantId, String systemDefaultTenantId,int flag);
	
	@Query(value = "SELECT count(*) FROM  cs_sales_flat_quote_item a left JOIN cs_sales_flat_quote b ON a.quote_id=b.entity_id WHERE a.prescription_required=1 and b.is_active=1 and b.entity_id=?1 and (b.tenant_id=?2 or b.tenant_id=?3)",nativeQuery = true)
	public Long fetchAllPrescriptionItemByEntityId(long quoteId,String tenantId, String systemDefaultTenantId,int flag);

}
