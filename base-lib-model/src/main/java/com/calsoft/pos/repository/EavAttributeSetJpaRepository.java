package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.eavattribute.EavAttributeSet;


public interface EavAttributeSetJpaRepository
		extends JpaRepository<EavAttributeSet, Long>, JpaSpecificationExecutor<EavAttributeSet> {

	EavAttributeSet findByAttributeSetId(long attributeSetId);

	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.attributeSetName = ?1  And  u.entityTypeId = 4 And (u.tenantId = ?2 OR u.tenantId = ?3)")
	EavAttributeSet findByattributeSetName(String attributeSetName, String tenantId, String baseAttribute);

	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.entityTypeId = 4 And (u.tenantId = ?1)")
	Page<EavAttributeSet> findAllEavAttributeSet(Pageable pageable, String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.entityTypeId = 4 And (u.tenantId = ?1 OR u.tenantId = ?2)")
	List<EavAttributeSet> findAllEavAttributeSet(String tenantId, String baseAttribute);

	@Query("SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.entityTypeId = 4 AND (u.attributeSetName LIKE %:search% OR u.attributeSetId LIKE %:search% ) And (u.tenantId = :tenantId ) ")
	Page<EavAttributeSet> searchForAttributeSet(String search,String tenantId,Pageable pageable);
	
	@Query("SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.attributeSetId = ?1  And u.tenantId =?2")
	EavAttributeSet fetchAttributeSetName(long attributeSetId,String tenantId);

	EavAttributeSet findByAttributeSetIdAndTenantId(Long attributeSetId, String tenantId);

	EavAttributeSet findByAttributeSetIdAndTenantIdOrTenantId(Long valueOf, String tenantId, String baseAttribute);
	
	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttributeSet(u.attributeSetId,u.attributeSetName) FROM EavAttributeSet u where u.entityTypeId = 4 And u.tenantId=?1")
	List<EavAttributeSet> findAllEavAttributeSetList(String tenantId);

}
