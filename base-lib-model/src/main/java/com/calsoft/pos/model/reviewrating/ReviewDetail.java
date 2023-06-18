package com.calsoft.pos.model.reviewrating;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "review_detail")
@Data
public class ReviewDetail implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detail_id")
	private int detailId;

	@Column(name = "store_id")
	private Integer storeId;

	@Column(name = "title")
	private String title;

	@Column(name = "detail")
	private String detail;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "customer_id")
	private Integer customerId;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "review_id")
	private Review review;

}
