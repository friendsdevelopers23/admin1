package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.coupon.SalesRuleCoupon;

public interface SalesRuleCouponJpaRepository extends JpaRepository<SalesRuleCoupon, Long> {

	@Query(value = "SELECT a FROM SalesRuleCoupon a LEFT JOIN SalesRule b ON a.ruleId=b.ruleId where a.code=?1  And b.tenantId = ?2")
	SalesRuleCoupon fetchCodeBasedOnClient(String couponCode, String tenantId);

	SalesRuleCoupon findByCode(String couponCode);

}
