package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>, JpaSpecificationExecutor<Categories> {
	Page<Categories> findByTenantId(Pageable pageable, String tenantId);

	Categories findByIdAndTenantId(long CategoriesId, String tenantId);

	List<Categories> findByTenantId(String tenantId);

}
