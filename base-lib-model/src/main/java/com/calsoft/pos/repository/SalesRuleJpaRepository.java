package com.calsoft.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.coupon.SalesRule;

@Repository
public interface SalesRuleJpaRepository extends JpaRepository<SalesRule, Long>, JpaSpecificationExecutor<SalesRule> {

	SalesRule findByRuleIdAndTenantId(long ruleId, String tenantId);

	SalesRule findByName(String couponName);

	SalesRule findByNameAndTenantId(String couponName, String tenantId);

	SalesRule findByRuleIdAndTenantIdAndIsActive(Long ruleId, String tenantId, int i);

	Page<SalesRule> findByTenantId(Pageable pageable, String tenantId);

	Page<SalesRule> findByTenantIdAndToDateAfterAndIsActiveAndIsVisible(Pageable pageable, String tenantId, Date toDate,
			int isActive, int visible);

	@Query(value = "SELECT a FROM SalesRule a where a.ruleId IN (?1)  And a.tenantId = ?2")
	List<SalesRule> fetchRuleId(List<Long> ruleId, String tenantId);
}
