package com.calsoft.pos.model.cart;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "core_website")
@Data
public class CoreWebsite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "website_id")
    private long websiteId;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "sort_order")
    private long sortOrder;
	
	@Column(name = "default_group_id")
    private long defaultGroupId;
	
	@Column(name = "is_default")
    private long isDefault;


	
	

}
