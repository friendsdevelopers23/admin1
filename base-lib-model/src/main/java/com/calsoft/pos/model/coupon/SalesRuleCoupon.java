package com.calsoft.pos.model.coupon;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "salesrule_coupon")
@Data
public class SalesRuleCoupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private Long couponId;

	@Column(name = "code")
	private String code;

	@Column(name = "usage_limit")
	private Long usageLimit;

	@Column(name = "usage_per_customer")
	private Long usagePerCustomer;

	@Column(name = "times_used")
	private Long timesUsed;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "is_primary")
	private Long isPrimary;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "type")
	private Long type;

	@Column(name = "rule_id", insertable = false, updatable = false)
	private Long ruleId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rule_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private SalesRule salesRule;

}
