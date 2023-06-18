package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor<Products> {
	Page<Products> findByTenantId(Pageable pageable, String tenantId);

	Products findByIdAndTenantId(long ProductsId, String tenantId);

	List<Products> findByTenantId(String tenantId);

}
