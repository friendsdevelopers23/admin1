package com.calsoft.pos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.productindex.ProductIDX2;

@Repository
public interface ProductIDx2Repository extends SolrCrudRepository<ProductIDX2, Long> {

	Page<ProductIDX2> findByCategoryIdAndStockStatus(long categoryId, int stockStatus, Pageable pageable);

	Page<ProductIDX2> findByStockStatus(int stockStatus, Pageable pageable);

	Page<ProductIDX2> findByCategoryIdAndEntityIdIsIn(long categoryId, List<Long> productIds, Pageable pageable);

	List<ProductIDX2> findByEntityId(long entityId);

	List<ProductIDX2> findByEntityId(BigInteger entityPk);

	List<ProductIDX2> deleteByEntityId(long entityId);

	@Query("name:*?0* OR description:*?0* OR shortDescription:*?0* OR entityId:*?0*")
	public Page<ProductIDX2> findByNamedQuery(String searchTerm, Pageable pageable);

	List<ProductIDX2> findByCategoryId(long entityId);

	public Page<ProductIDX2> findAll(Pageable pageable);

	/*
	 * @AuditMe(saudit = AuditData.class, pkField = "entityId")
	 * 
	 * @Override <S extends ProductIDX2> Iterable<S> saveAll(Iterable<S> iterable);
	 */
	
	/*
	 * @AuditMe(saudit = AuditData.class, pkField = "entityId")
	 * 
	 * @Override <S extends ProductIDX2> S save(S s);
	 */

	public Page<ProductIDX2> findDistinctByEntityId(Pageable pageable);

	@Query("entityId:?0 OR sku:?0 OR  name:*?0*")
	public Page<ProductIDX2> findByNamedQueryAdmin(String searchTerm, Pageable pageable);

	Page<ProductIDX2> findByTenantId(Pageable pageable, String tenantId);

	List<ProductIDX2> findByEntityIdAndTenantId(long entityId, String tenantId);

	List<ProductIDX2> findByCategoryIdAndTenantId(Long entityId, String tenantId);
	
	Page<ProductIDX2> findByCategoryNamesAndStockStatus(String name,int stockStatus, Pageable pageable);
	
	Page<ProductIDX2> findByImagesAndTenantId(String name,String tenantId, Pageable pageable);
	
	ProductIDX2 findByNameAndTenantId(String name, String tenantId);

	List<ProductIDX2> findFirstByCategoryIdAndTenantIdOrderByPriceDesc(Long entityId, String tenantId);

	List<ProductIDX2> findFirstByCategoryIdAndTenantIdOrderByPriceAsc(Long entityId, String tenantId);
	
	List<ProductIDX2> findFirstByCategoryNamesAndTenantIdOrderByPriceAsc(String name, String tenantId);
	
	List<ProductIDX2> findFirstByCategoryNamesAndTenantIdOrderByPriceDesc(String name, String tenantId);

	Page<ProductIDX2> findByCategoryIdAndStockStatusAndTenantId(int categoryId, int stockStatus, Pageable paging1, String tenantId);

	List<ProductIDX2> deleteByTenantId(String tenantId);

	Page<ProductIDX2> findByAttributeSetIdAndTenantId(int setId, String tenantId, Pageable pageableCount);

	void deleteByTenantIdAndAttributeSetId(String tenantId, int setId);

	List<ProductIDX2> findByTenantIdAndCategoryNames(String tenantId,String productName);

	Page<ProductIDX2> findByCategoryNamesAndStockStatusAndTenantId(String categoryId, int i, Pageable pageable,
			String tenantId);

	Page<ProductIDX2> findByStockStatusAndTenantId(int i, Pageable pageable, String tenantId);

	ProductIDX2 findBySkuAndTenantId(String type, String tenantId);
	
	Page<ProductIDX2> findByTenantIdAndEntityIdNotIn(String tenantId,List<Long> productId, Pageable pageable);
	
	Page<ProductIDX2> findByNameLikeOrSkuLikeAndTenantIdAndEntityIdNotIn(String name,String sku,String tenantId,List<Long> productId, Pageable pageable);
	
	List<ProductIDX2> findByTenantIdAndEntityIdIn(String tenantId,List<Long> productId);

	List<ProductIDX2> findByVisibilityInAndCategoryIdAndTenantIdAndIsInStock(List<Long> visibility, Long categoryId,
			String tenantId, int stock);
	
	List<ProductIDX2> findByTenantIdAndVisibility(String tenantId,Long visibility);
}
