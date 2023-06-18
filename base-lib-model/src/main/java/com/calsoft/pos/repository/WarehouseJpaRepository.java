package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Warehouse;

@Repository

public interface WarehouseJpaRepository extends JpaRepository<Warehouse, Long>, JpaSpecificationExecutor<Warehouse> {

	Warehouse findByIdAndTenantId(long WarehouseId,String tenantId);

	Page<Warehouse> findByTenantId(Pageable pageable, String tenantId);
	
	List<Warehouse> findByTenantIdAndIsActive(String tenantId,int i);

}
