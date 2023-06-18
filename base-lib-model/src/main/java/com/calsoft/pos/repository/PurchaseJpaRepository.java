package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.purchase.Purchase;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<Purchase, Long>, JpaSpecificationExecutor<Purchase> {

	Purchase findByIdAndTenantId(long supplierId, String tenantId);

	Page<Purchase> findByTenantId(Pageable pageable, String tenantId);

	List<Purchase> findByTenantId(String tenantId);

}
