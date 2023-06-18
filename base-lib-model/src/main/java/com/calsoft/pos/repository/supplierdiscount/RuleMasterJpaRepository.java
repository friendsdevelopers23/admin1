package com.calsoft.pos.repository.supplierdiscount;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.supplierdiscount.RuleMaster;

@Repository
public interface RuleMasterJpaRepository extends JpaRepository<RuleMaster ,Long>, JpaSpecificationExecutor<RuleMaster>{

	RuleMaster findByRuleIdAndTenantId(long ruleId, String tenantId);
	
	Page<RuleMaster> findByTenantId(Pageable pageable, String tenantId);

	Page<RuleMaster> findByTenantIdAndType(Pageable pageable, String tenantId,int type);
	
	List<RuleMaster> findByTypeAndTenantId(int type, String tenantId);
	
	
	@Query(value = "SELECT a FROM RuleMaster a where a.ruleId IN (?1)  And a.tenantId = ?2 order by a.priority asc,a.ruleId asc")
	List<RuleMaster> fetchRuleMaster(List<Long> ruleId, String tenantId);
	



}
