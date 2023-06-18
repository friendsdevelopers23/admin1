package com.calsoft.pos.model.reviewrating;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "rating_store")
@Data
public class RatingStore implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Column(name = "store_id")
	private int storeId;

	@JsonBackReference
	@OneToOne
	@Id
	@JoinColumn(name = "rating_id")
	private Rating rating;

}
