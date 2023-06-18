package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.PriceGroups;

@Repository
public interface PriceGroupsRepository extends JpaRepository<PriceGroups, Long>, JpaSpecificationExecutor<PriceGroups> {
	Page<PriceGroups> findByTenantId(Pageable pageable, String tenantId);

	PriceGroups findByIdAndTenantId(long PriceGroupsId, String tenantId);

	List<PriceGroups> findByTenantId(String tenantId);

}