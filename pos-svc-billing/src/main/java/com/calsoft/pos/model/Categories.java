package com.calsoft.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;
	
	@Column(name = "Parent_Category")
	private String parentCategory;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "slug")
	private String slug;

	@Column(name = "description")
	private String description;

	@Column(name = "ecom_id")
	private Long ecomId;

	@Column(name = "ecom_path")
	private String ecomPath;
	


	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
