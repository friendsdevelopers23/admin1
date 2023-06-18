package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.eavattribute.EavAttribute;

@Repository

public interface EavAttributeJpaRepository
		extends JpaRepository<EavAttribute, Long>, JpaSpecificationExecutor<EavAttribute> {

	EavAttribute findByAttributeId(long attributeId);

	EavAttribute findByAttributeCode(String attributeCode);

	EavAttribute findByfrontendLabel(String frontendLabel);

	@Query(value = "SELECT u  FROM EavAttribute u  where  u.entityTypeId=4 and\n"
			+ "u.frontendInput is not null and u.frontendLabel is not null and\n"
			+ "u.attributeId NOT IN(?2) and u.tenantId=?3  and u.attributeId NOT IN (\n"
			+ "SELECT v.attributeId FROM EavEntityAttribute v where v.attributeSetId=?1 and v.entityTypeId=4)")
	public Page<EavAttribute> fetchAllUnAssignedAttributes(long attributeSetId, List<Long> assignedAttributeId,
			String tenantId, Pageable pageable);

	@Query(value = " SELECT u FROM EavAttribute u  where \n" + "u.entityTypeId=4 AND u.frontendInput is not null\n"
			+ "AND u.frontendLabel is not null AND\n"
			+ "u.attributeId NOT IN(?2) and u.tenantId=?4 AND u.attributeId NOT IN (\n"
			+ "SELECT v.attributeId FROM EavEntityAttribute v where v.attributeSetId=?1 AND v.entityTypeId=4\n"
			+ ")AND (u.attributeCode LIKE %?3%  OR u.frontendLabel LIKE %?3%\n" + " OR u.frontendInput LIKE %?3%)")
	public Page<EavAttribute> fetchSearchAllUnAssignedAttributes(long attributeSetId, List<Long> assignedAttributeId,
			String search, String tenantId, Pageable pageable);

	///

	Page<EavAttribute> findByTenantIdOrTenantId(Pageable pageable, String tenantId, String baseAttribute);

	Page<EavAttribute> findByTenantIdOrTenantIdAndEntityTypeId(Pageable pageable, String tenantId, String baseAttribute,
			long entityTypeId);

	Page<EavAttribute> findByTenantIdAndEntityTypeId(Pageable pageable, String tenantId, long entityTypeId);

	EavAttribute findByAttributeCodeAndTenantId(String attributeCode, String tenantId);
	
	EavAttribute findByFrontendLabelAndTenantId(String frontendLabel,String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttribute(u.attributeId) FROM EavAttribute u where u.frontendLabel = ?1  And  u.entityTypeId = ?2 And (u.tenantId = ?3 OR u.tenantId = ?4)")
	EavAttribute findByfrontendLabelAndTenantId(String frontendLabel,long entityTypeId,String tenantId, String baseAttribute);

	EavAttribute findByAttributeIdAndTenantId(Long attributeId, String tenantId);

	@Query(value = "SELECT new com.calsoft.pos.model.eavattribute.EavAttribute(u.attributeId) FROM EavAttribute u where u.attributeCode = ?1  And  u.entityTypeId = ?2 And (u.tenantId = ?3 OR u.tenantId = ?4)")
	EavAttribute findByAttributeCode(String attributeCode,long entityTypeId,String tenantId, String baseAttribute);

	@Transactional
	@Modifying // to mark delete or update query
	@Query(value = "DELETE FROM EavAttribute e WHERE e.tenantId = ?1 and e.attributeId=?2")
	void deleteByAttributeId(String tenantId, Long attributeId);
	
	@Query(value="SELECT  new com.calsoft.pos.model.eavattribute.EavAttribute(a.frontendLabel) FROM EavAttribute a INNER JOIN CatalogEavAttribute b ON a.attributeId=b.attributeId where a.frontendLabel IN (?1) And a.tenantId = ?2 order by b.position")
	List<EavAttribute> findAttributeByPosition(List<String> path,String tenantId);

}
