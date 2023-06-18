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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eav_attribute_set")
@Getter
@Setter
public class EavAttributeSet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attribute_set_id")
	private long attributeSetId;

	@Column(name = "entity_type_id")
	private long entityTypeId;

	@Column(name = "attribute_set_name")
	private String attributeSetName;

	@Column(name = "sort_order")
	private long sortOrder;

	@Transient
	private String setName;

	@Transient
	private int basedOn;

	@Transient
	private String operationType;

	@OneToMany(mappedBy = "eavAttributeSet", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	@OrderBy("sortOrder asc")
	private List<EavAttributeGroup> eavAttributeGroup = new ArrayList<EavAttributeGroup>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;
	
	@Transient
	private List<Long> value = new ArrayList<Long>();
	
	@Transient
	private List<Long> removedFilterAttributeId = new ArrayList<Long>();

	public EavAttributeSet(long attributeSetId, String attributeSetName) {
		this.attributeSetId = attributeSetId;
		this.attributeSetName = attributeSetName;
	}

	public EavAttributeSet() {
		super();
	}

}
