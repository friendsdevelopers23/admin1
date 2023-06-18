package com.calsoft.pos.model.reviewrating;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private long reviewId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "entity_pk_value")
	private int entityPkValue;

	@Column(name = "status_id")
	private int statusId;

	@OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
	@JsonManagedReference
	private ReviewDetail reviewDetail;

	@Transient
	private String productName;

	@Transient
	private String customerName;

	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RatingOptionVote> ratingOptionVote = new ArrayList<RatingOptionVote>();

	@Transient
	private String operationType;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
