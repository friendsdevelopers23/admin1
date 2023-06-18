package com.calsoft.pos.model.coupon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "salesrule")
@Data
public class SalesRule implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id")
	private Long ruleId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "from_date")
	private Date fromDate;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "uses_per_customer")
	private Long usesPerCustomer;

	@Column(name = "uses_per_coupon")
	private Long usesPerCoupon;

	@Column(name = "use_auto_generation")
	private Long useAutoGeneration;

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

	@Column(name = "times_used")
	private Long timesUsed;

	@Column(name = "meta_data")
	private String metaData;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "salesRule", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference
	public List<SalesRuleCoupon> salesRuleCoupon = new ArrayList<SalesRuleCoupon>();

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

}
