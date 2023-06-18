package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calsoft.pos.model.coupon.SalesRuleCustomer;

public interface SalesRuleCustomerJpaRepository extends JpaRepository<SalesRuleCustomer, Long> {

	List<SalesRuleCustomer> findByRuleIdAndCustomerId(Long ruleId, long customerId);

}
