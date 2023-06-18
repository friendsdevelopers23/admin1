package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Kitchen_MasterModel;

@Repository
public interface Kitchen_MasterRepository
		extends JpaRepository<Kitchen_MasterModel, Long>, JpaSpecificationExecutor<Kitchen_MasterModel> {
	Page<Kitchen_MasterModel> findByTenantId(Pageable pageable, String tenantId);

	Kitchen_MasterModel findByIdAndTenantId(long KitchenId, String tenantId);

	List<Kitchen_MasterModel> findByTenantId(String tenantId);

}
