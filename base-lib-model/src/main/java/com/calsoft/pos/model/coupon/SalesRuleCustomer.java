package com.calsoft.pos.model.coupon;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "salesrule_customer")
@Data
public class SalesRuleCustomer implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_customer_id")
	private Long ruleCustomerId;

	@Column(name = "rule_id")
	private Long ruleId;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "times_used")
	private Long timesUsed;
	
	
}
