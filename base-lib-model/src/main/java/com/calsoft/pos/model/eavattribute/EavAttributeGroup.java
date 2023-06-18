package com.calsoft.pos.model.eavattribute;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "eav_attribute_group")
@Data
public class EavAttributeGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attribute_group_id")
	private long attributeGroupId;

	@Column(name = "attribute_group_name")
	private String attributeGroupName;

	@Column(name = "sort_order")
	private long sortOrder;

	@Column(name = "default_id")
	private long defaultId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_set_id")
	private EavAttributeSet eavAttributeSet;

	@OneToMany(mappedBy = "eavAttributeGroup", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<EavEntityAttribute> eavEntityAttribute = new ArrayList<EavEntityAttribute>();

}
