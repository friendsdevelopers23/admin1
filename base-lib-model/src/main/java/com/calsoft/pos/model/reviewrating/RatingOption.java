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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "rating_option")
@Data
public class RatingOption implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private int optionId;

	@Column(name = "code")
	private String code;

	@Column(name = "value")
	private int value;

	@Column(name = "position")
	private int position;

	@JsonBackReference()
	@ManyToOne
	@JoinColumn(name = "rating_id")
	private Rating rating;


}
