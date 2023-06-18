package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "newsletter_subscriber")
@Data
public class NewsletterSubscriber implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscriber_id")
	private long subscriberId;

	@Column(name = "store_id")
	private Integer storeId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "change_status_at", updatable = false)
	private Date changeStatusAt;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "subscriber_email")
	private String subscriberEmail;

	@Column(name = "subscriber_status")
	private int subscriberStatus;

	@Column(name = "subscriber_confirm_code")
	private String subscriberConfirmCode;

	@Transient
	private String operationType;

	@Transient
	private String customerName;

	@Transient
	private String status;
	
	@JsonIgnore
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
