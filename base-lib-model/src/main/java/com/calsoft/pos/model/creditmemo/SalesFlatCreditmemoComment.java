package com.calsoft.pos.model.creditmemo;

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

import lombok.Data;

@Entity
@Table(name = "sales_flat_creditmemo_comment")
@Data
public class SalesFlatCreditmemoComment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SalesFlatCreditmemo salesFlatCreditmemo;

	@Column(name = "is_customer_notified")
	private long isCustomerNotified;

	@Column(name = "is_visible_on_front")
	private long isVisibleOnFront;

	@Column(name = "comment")
	private String comment;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

}
