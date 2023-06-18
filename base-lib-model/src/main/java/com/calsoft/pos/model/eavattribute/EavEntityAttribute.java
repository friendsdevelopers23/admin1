package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eav_entity_attribute")
@Getter
@Setter
public class EavEntityAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_attribute_id")
	private long entityAttributeId;

	@Column(name = "entity_type_id")
	private long entityTypeId;

	@Column(name = "attribute_set_id")
	private long attributeSetId;

	@Column(name = "attribute_id", insertable = false, updatable = false)
	private long attributeId;

	@Column(name = "sort_order")
	private long sortOrder;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "attribute_group_id")
	private EavAttributeGroup eavAttributeGroup;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "attribute_id", unique = true)
	private EavAttribute eavAttribute;

	@Transient
	private String operationType;

	public EavEntityAttribute() {
		super();
	}

	public EavEntityAttribute(long attributeSetId) {
		this.attributeSetId = attributeSetId;
	}

}
