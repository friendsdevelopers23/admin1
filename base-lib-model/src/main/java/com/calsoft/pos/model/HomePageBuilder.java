package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "core_config_layout")
public class HomePageBuilder implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "layout_config_id")
	private int layoutConfigId;

	@Column(name = "position")
	private int position;

	@Column(name = "type")
	private String type;

	@Column(name = "value")
	private String value;

	@Column(name = "title")
	private String title;

	@Column(name = "layout")
	private String layout;
	
	@Column(name = "sort_order")
	private String sortOrder;

	@Column(name = "custom_field1")
	private String customField1;

	@Column(name = "custom_field2")
	private String customField2;

	@Column(name = "custom_field3")
	private String customField3;

	@Column(name = "custom_field4")
	private String customField4;

	@Column(name = "custom_field5")
	private String customField5;

	@Column(name = "custom_field6")
	private String customField6;

	@Column(name = "custom_field7")
	private String customField7;

	@Column(name = "custom_field8")
	private String customField8;

	@Column(name = "custom_field9")
	private String customField9;

	@Column(name = "custom_field10")
	private String customField10;
	
	@Column(name = "categoryId1")
	private int categoryId1;
	
	@Column(name = "categoryId2")
	private int categoryId2;
	
	@Column(name = "categoryId3")
	private int categoryId3;
	
	@Column(name = "categoryId4")
	private int categoryId4;
	
	@Column(name = "categoryId5")
	private int categoryId5;
	
	@Column(name = "categoryId6")
	private int categoryId6;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@OneToMany(mappedBy = "homePageBuilder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<HomePageBuilderImage> images = new ArrayList<HomePageBuilderImage>();
	
	@Column(name = "page_type")
	private int pageType;

}
