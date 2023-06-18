package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.productcustomization.ProductCustomization;

public interface ProductCustomizationRepository
		extends JpaRepository<ProductCustomization, Long>, JpaSpecificationExecutor<ProductCustomization> {

	Page<ProductCustomization> findByTenantId(Pageable pageable, String tenantId);

	@Query("select new com.calsoft.pos.model.productcustomization.ProductCustomization(a.valueId,a.value) from ProductCustomization a where a.tenantId=?1")
	List<ProductCustomization> findByTenantId(String tenantId);

	ProductCustomization findByValueIdAndTenantId(Integer valueOf, String tenantId);

	List<ProductCustomization> findByTenantIdAndValueIdIn(String tenantId, List<Integer> valuesList);

}
