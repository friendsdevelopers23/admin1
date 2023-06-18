package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ReferalOrderItems;

@Repository
public interface ReferalOrderItemsRepository
		extends JpaRepository<ReferalOrderItems, Long>, JpaSpecificationExecutor<ReferalOrderItems> {

	Page<ReferalOrderItems> findByTenantId(Pageable pageable, String tenantId);

	ReferalOrderItems findByItemIdAndTenantId(long referalCustomerId, String tenantId);

}
