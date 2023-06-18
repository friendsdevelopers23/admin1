package com.calsoft.pos.model.supplierdiscount;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "rule_master")
@Data
public class RuleMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id")
	private Long ruleId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "conditions_serialized")
	private String conditionsSerialized;

	@Column(name = "actions_serialized")
	private String actionsSerialized;

	@Column(name = "stop_rules_processing")
	private Integer stopRulesProcessing;

	@Column(name = "is_advanced")
	private Integer isAdvanced;

	@Column(name = "sort_order")
	private Integer sortOrder;

	@Column(name = "simple_action")
	private String simpleAction;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "discount_qty")
	private Double discountQty;

	@Column(name = "discount_step")
	private Double discountStep;

	@Column(name = "simple_free_shipping")
	private Integer simpleFreeShipping;

	@Column(name = "apply_to_shipping")
	private Integer applyToShipping;

	@Column(name = "coupon_type")
	private Integer couponType;

	@Column(name = "is_rss")
	private Integer isRss;

	@Column(name = "product_ids")
	private String productIds;

	@Column(name = "meta_data")
	private String metaData;

	@Column(name = "type")
	private int type;

	@Transient
	private String codeCoupon;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private String operationType;

	@Column(name = "max_discount_amount")
	private Double maxDiscountAmount;

	@Column(name = "is_visible")
	private int isVisible;

	@Column(name = "priority")
	private long priority;
}
