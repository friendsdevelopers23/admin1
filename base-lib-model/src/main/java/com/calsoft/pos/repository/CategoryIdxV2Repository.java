package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.audit.AuditData;
import com.calsoft.pos.model.audit.AuditMe;
import com.calsoft.pos.model.categoryindex.CategoryIDX;

@Repository
public interface CategoryIdxV2Repository extends SolrCrudRepository<CategoryIDX, Long> {

	List<CategoryIDX> findByParentId(String parentId);

	CategoryIDX findByCategoryId(long categoryId);

	CategoryIDX findByCategoryIdAndIsActive(long categoryId, String isActive);

	List<CategoryIDX> findByCategoryIdIn(List<Long> path);

	@Query("tenantId:?2 AND name:*?0* OR categoryId:*?0* OR parentId:*?0* OR isActive:*?0* ")
	public Page<CategoryIDX> findByNamedQuery(String searchTerm, Pageable pageable, String tenantId);

	/*
	 * @AuditMe(saudit = AuditData.class, pkField = "categoryId")
	 * 
	 * @Override <S extends CategoryIDX> Iterable<S> saveAll(Iterable<S> iterable);
	 */

	/*
	 * @AuditMe(saudit = AuditData.class, pkField = "categoryId")
	 * 
	 * @Override <S extends CategoryIDX> S save(S s);
	 */
	
	
	List<CategoryIDX> findByTenantIdAndCategoryNameIn(String tenantId,String[] name);

	Page<CategoryIDX> findByTenantId(Pageable pageable, String tenantId);

	Iterable<CategoryIDX> findByTenantId(String tenantId);

	CategoryIDX findByCategoryIdAndTenantId(Long categoryId, String tenantId);

	CategoryIDX findByCategoryNameAndTenantId(String categoryName, String tenantId);

	List<CategoryIDX> findByTenantIdAndParentIdAndIsActiveAndIncludeInMenuOrderByCategoryNameAsc(String tenantId,
			String parentId, String isActive, String includeInMenu);

	List<CategoryIDX> findByTenantIdAndParentIdAndIsActiveAndIncludeInMenuOrderByPositionAsc(String tenantId,
			String parentId, String isActive, String includeInMenu);

	Page<CategoryIDX> findByTenantIdAndParentIdAndIsActiveAndIncludeInMenuOrderByPositionAsc(String tenantId,
			String parentId, String isActive, String includeInMenu, Pageable pageable);

	List<CategoryIDX> findByCategoryIdInAndTenantId(List<Long> path, String tenantId);

	List<CategoryIDX> findByCategoryIdInAndTenantIdAndIsActive(List<Long> path, String tenantId, String isActive);

	List<CategoryIDX> deleteByTenantId(String tenantId);

	CategoryIDX findByCategoryIdAndIsActiveAndTenantId(Long categoryId, String isActive, String tenantId);

	CategoryIDX findByCategoryNameAndIsActiveAndTenantId(String categoryName, String isActive, String tenantId);

	Iterable<CategoryIDX> findByParentIdAndIsActiveAndTenantId(String categoryId, String string, String tenantId);

	Iterable<CategoryIDX> findByTenantIdAndIsActive(String tenantId, String string);
	
	Iterable<CategoryIDX> findByParentIdAndTenantId(String categoryId,String tenantId);
	
	List<CategoryIDX> findByTenantIdAndParentIdOrderByCategoryNameAsc(String tenantId,String parentId);

	Iterable<CategoryIDX> findByParentIdAndTenantIdAndDisplayModeNotIn(String string, String tenantId,
			List<Long> categoryId);

	Iterable<CategoryIDX> findByParentIdAndIsActiveAndTenantIdAndDisplayModeNotIn(String string, String string2,
			String tenantId, List<Long> categoryId);

	List<CategoryIDX> findByTenantIdAndParentIdAndIsActiveOrderByCategoryNameAsc(String tenantId, String valueOf,
			String string);

	Iterable<CategoryIDX> findByIsActiveAndTenantIdAndDisplayMode(String string, String tenantId, String string2);

	Iterable<CategoryIDX> findByIsActiveAndTenantIdAndDisplayModeIn(String string, String tenantId,
			List<Long> categoryId);

}
