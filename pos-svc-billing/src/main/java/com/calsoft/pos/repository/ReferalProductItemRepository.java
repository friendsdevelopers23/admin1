package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ReferalProductItem;

@Repository

public interface ReferalProductItemRepository
		extends JpaRepository<ReferalProductItem, Long>, JpaSpecificationExecutor<ReferalProductItem> {

	Page<ReferalProductItem> findByTenantId(Pageable pageable, String tenantId);

	ReferalProductItem findByProductIdAndTenantId(long referalCustomerId, String tenantId);

}
