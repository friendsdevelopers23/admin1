package com.calsoft.pos.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.category.CategoryEntity;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

	public CategoryEntity findByEntityIdAndTenantId(int entityId,String tenantId);

	@Query("select distinct c.entityId from CategoryEntity c where c.tenantId=?1")
	public Page<Integer> findDistinctByEntityId(String tentantId,Pageable pageable);
	
	@Query("select count(*) from CategoryEntity c where c.tenantId=?1")
	public Integer findDistinctByEntityIdCount(String tentantId);
	
	@Query("select count(*) from CategoryEntity c where c.path LIKE %?1%")
	public Integer findNumberOfMenu(String path);
	
	@Query("select c.entityId from CategoryEntity c where c.path LIKE %?1%")
	public List<Integer> findPath(String path);
}
