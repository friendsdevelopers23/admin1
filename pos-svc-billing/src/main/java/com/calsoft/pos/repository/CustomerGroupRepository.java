package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CustomerGroup;

@Repository
public interface CustomerGroupRepository
		extends JpaRepository<CustomerGroup, Long>, JpaSpecificationExecutor<CustomerGroup> {
	Page<CustomerGroup> findByTenantId(Pageable pageable, String tenantId);

	CustomerGroup findByCustomerGroupIdAndTenantId(long CustomerGroupId, String tenantId);

	List<CustomerGroup> findByTenantId(String tenantId);

}
