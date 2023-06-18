package com.calsoft.pos.model;

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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "core_config_layout_image")
public class HomePageBuilderImage implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private int valueId;

	@Column(name = "value")
	private String img;

	@Column(name = "navigation")
	private int navigation;
	
	@Column(name = "navigated_url")
	private String navigatedUrl;

	@Column(name = "categoryId")
	private int catId;
	
	@Column(name = "type")
	private int type;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "layout_config_id")
	private HomePageBuilder homePageBuilder;

}
