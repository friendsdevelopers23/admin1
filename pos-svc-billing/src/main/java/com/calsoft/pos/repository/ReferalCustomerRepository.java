package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ReferalCustomer;

@Repository
public interface ReferalCustomerRepository
		extends JpaRepository<ReferalCustomer, Long>, JpaSpecificationExecutor<ReferalCustomer> {

	Page<ReferalCustomer> findByTenantId(Pageable pageable, String tenantId);

	ReferalCustomer findByProductIdAndTenantId(long referalCustomerId, String tenantId);

}