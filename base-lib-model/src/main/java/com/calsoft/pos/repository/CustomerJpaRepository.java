
package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.customer.CustomerEntity;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
	public CustomerEntity findByEntityIdAndTenantId(int entityId, String tenantId);
	
	@Query("select c from CustomerEntity c where c.entityId=?1 and (c.tenantId=?2 or c.tenantId=?3)")
	public CustomerEntity findCustomer(int entityId, String tenantId,String primaryTenant);

	@Query("select c from CustomerEntity c where c.email=?1 and c.tenantId=?2")
	public CustomerEntity fetchByEmail(String email, String tenantId);
	
	
	@Query("select distinct c.entityId from CustomerEntity c where c.tenantId=?1")
	public Page<Integer> findDistinctByEntityId(String tenantId, Pageable pageable);

	@Query("select count(*) from CustomerEntity c where c.tenantId=?1")
	public Integer findDistinctByEntityIdCount(String tentantId);
}
