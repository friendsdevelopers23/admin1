package com.calsoft.pos.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.product.CatalogCategoryProduct;


public interface CatalogCategoryProductRepository extends JpaRepository<CatalogCategoryProduct, Long> {

	List<CatalogCategoryProduct> findByProductId(long productId);

	//
	List<CatalogCategoryProduct> findByTenantId(String tenantId);

	List<CatalogCategoryProduct> findByProductIdAndTenantId(long productId, String tenantId);
	
	@Query("select  count(distinct c.productId)  from CatalogCategoryProduct c where c.tenantId=?1")
	public Long findDistinctByEntityIdCount(String tentantId);
	
	@Query("select distinct new com.calsoft.pos.model.product.CatalogCategoryProduct(c.productId,c.categoryId) from CatalogCategoryProduct c where c.tenantId=?1 group by c.productId")
	Page<CatalogCategoryProduct> findByTenantId(String tenantId,Pageable pageable);
	
	@Query("select c.categoryId from CatalogCategoryProduct c where c.productId=?1")
	List<Long> findByCategoryIdWithProductId(long productId);
}
