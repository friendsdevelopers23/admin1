package com.calsoft.pos.model.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "referal_view")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "referal_customer_id")
	private long referalCustomerId;

	@Column(name = "referral_commision_individual")
	private String referralCommisionIndividual;

	@Column(name = "a.referral_commision_cancelled")
	private String aReferralCommisionCancelled;

	@Column(name = "a.referral_commission_refunded")
	private String aReferralCommissionRefunded;

	@Column(name = "user_name")
	private String userName;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;


}
