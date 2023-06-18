package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "eav_attribute_option")
@Data
public class EavAttributeOption implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private long optionId;

	@Column(name = "sort_order")
	private long sortOrder;
	
	@Column(name = "attribute_id",insertable=false,updatable=false)
	private long attributeId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "attribute_id")
	private EavAttribute eavAttribute;

	@OneToMany(mappedBy = "eavAttributeOption", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EavAttributeOptionValue> eavAttributeOptionValue = new ArrayList<EavAttributeOptionValue>();



}
