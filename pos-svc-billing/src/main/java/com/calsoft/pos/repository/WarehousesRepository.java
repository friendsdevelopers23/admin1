package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.WarehousesModel;

@Repository
public interface WarehousesRepository
		extends JpaRepository<WarehousesModel, Long>, JpaSpecificationExecutor<WarehousesModel> {

	Page<WarehousesModel> findByTenantId(Pageable pageable, String tenantId);

	WarehousesModel findByIdAndTenantId(long WarehousesId, String tenantId);

	List<WarehousesModel> findByTenantId(String tenantId);

}
