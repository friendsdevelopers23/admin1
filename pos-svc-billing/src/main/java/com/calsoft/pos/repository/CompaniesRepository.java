package com.calsoft.pos.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CompaniesModel;
@Repository
public interface CompaniesRepository
		extends JpaRepository<CompaniesModel, Long>, JpaSpecificationExecutor<CompaniesModel> {
	Page<CompaniesModel> findByTenantId(Pageable pageable, String tenantId);

	CompaniesModel findByIdAndTenantId(long CompaniesId, String tenantId);

	List<CompaniesModel> findByTenantId(String tenantId);

}
