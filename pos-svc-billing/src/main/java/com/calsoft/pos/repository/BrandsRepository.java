package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.BrandsModel;

@Repository
public interface BrandsRepository extends JpaRepository<BrandsModel, Long>, JpaSpecificationExecutor<BrandsModel> {
	Page<BrandsModel> findByTenantId(Pageable pageable, String tenantId);

	BrandsModel findByIdAndTenantId(long BrandsId, String tenantId);

	List<BrandsModel> findByTenantId(String tenantId);

}
