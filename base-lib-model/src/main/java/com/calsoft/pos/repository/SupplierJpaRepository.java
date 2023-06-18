package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Supplier;

@Repository

public interface SupplierJpaRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

	Supplier findByIdAndTenantId(long supplierId,String tenantId);

	Page<Supplier> findByTenantId(Pageable pageable, String tenantId);
	
	
	List<Supplier> findByTenantId(String tenantId);
	
	@Query("select distinct c.company from Supplier c where c.tenantId=?1")
	public List<String> fetchAllDistinctCompanyName(String tentantId);
	
	@Query("select distinct c.id from Supplier c where c.company=?1")
	public Long fetchAllDistinctSupplierId(String companyName);
}
