package com.calsoft.pos.model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_order_status_history")
@Getter
@Setter
public class SalesFlatOrderStatusHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@Column(name = "is_customer_notified")
	private long isCustomerNotified;

	@Column(name = "is_visible_on_front")
	private long isVisibleOnFront;

	@Column(name = "comment")
	private String comment;

	@Column(name = "status")
	private String status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@Column(name = "entity_name")
	private String entityName;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id",nullable = true)
	private SalesFlatOrder salesFlatOrder;

}
