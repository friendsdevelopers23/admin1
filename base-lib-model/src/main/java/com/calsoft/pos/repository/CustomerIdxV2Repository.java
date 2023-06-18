package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.audit.AuditData;
import com.calsoft.pos.model.audit.AuditMe;
import com.calsoft.pos.model.customerindex.CustomerIDX;
import com.calsoft.pos.model.productindex.ProductIDX2;

@Repository
public interface CustomerIdxV2Repository extends SolrCrudRepository<CustomerIDX, Long> {

	CustomerIDX findByEntityId(long entityId);

	CustomerIDX findByEmailAndTenantId(String email, String tenantId);

	CustomerIDX findByFirstName(String firstName);

	@Query("firstName:*?0* OR entityId:*?0* OR email:*?0* OR isActive:*?0* OR lastName:*?0* OR dob:*?0*")
	public Page<CustomerIDX> findByNamedQuery(String searchTerm, Pageable pageable);

//	@AuditMe(saudit = AuditData.class, pkField = "entityId")
//	@Override
//	<S extends CustomerIDX> S save(S s);

	CustomerIDX findByEntityIdAndTenantId(Integer integer, String tenantId);

	Page<CustomerIDX> findByTenantId(Pageable pageable, String tenantId);

	CustomerIDX findByFirstNameAndTenantId(String customerName, String tenantId);

	CustomerIDX findByEntityIdAndTenantId(Long valueOf, String tenantId);

	CustomerIDX findByEntityId(Long entityId);

	List<CustomerIDX> deleteByTenantId(String tenantId);

	List<CustomerIDX> findByTenantIdAndIsActive(String tenantId, String isActive);

	List<CustomerIDX> findByTenantId(String tenantId);

}
