package com.calsoft.pos.model.reviewrating;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rating_option_vote")
@Getter
@Setter
public class RatingOptionVote implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_id")
	private long voteId;

	@Column(name = "remote_ip")
	private String remoteIp;

	@Column(name = "option_id")
	private int optionId;

	@Column(name = "remote_ip_long")
	private long remoteIpLong;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "entity_pk_value")
	private long entityPkValue;

	@Column(name = "percent")
	private int percent;

	@Column(name = "value")
	private double value;

	@Column(name = "rating_id")
	private int ratingId;
	
	@Column(name = "review_id",insertable=false,updatable=false)
	private long reviewId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@Transient
	private Double count;
	
	@Transient
	private long averageRating;

	public RatingOptionVote() {
		super();
	}

	public RatingOptionVote(long entityPkValue, Double count, long averageRating) {
		this.entityPkValue = entityPkValue;
		this.count = count;
		this.averageRating = averageRating;
	}



}
