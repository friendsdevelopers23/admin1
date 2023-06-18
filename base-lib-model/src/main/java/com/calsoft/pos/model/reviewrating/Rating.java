package com.calsoft.pos.model.reviewrating;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id")
	private int ratingId;

	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "rating_code")
	private String ratingCode;

	@Column(name = "position")
	private int position;

	@OneToOne(mappedBy = "rating", cascade = CascadeType.ALL)
	@JsonManagedReference
	private RatingTitle ratingTitle;

	@OneToOne(mappedBy = "rating", cascade = CascadeType.ALL)
	@JsonManagedReference
	private RatingStore ratingStore;

	@OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<RatingOption> ratingOption = new ArrayList<RatingOption>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
