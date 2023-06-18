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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eav_attribute")
@Getter
@Setter
public class EavAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attribute_id")
	private long attributeId;

	@Column(name = "entity_type_id")
	private long entityTypeId;

	@Column(name = "attribute_code")
	private String attributeCode;

	@Column(name = "attribute_model")
	private String attributeModel;

	@Column(name = "backend_model")
	private String backendModel;

	@Column(name = "backend_type")
	private String backendType;

	@Column(name = "backend_table")
	private String backendTable;

	@Column(name = "frontend_model")
	private String frontendModel;

	@Column(name = "frontend_input")
	private String frontendInput;

	@Column(name = "frontend_label")
	private String frontendLabel;

	@Column(name = "frontend_class")
	private String frontendClass;

	@Column(name = "source_model")
	private String sourceModel;

	@Column(name = "is_required")
	private long isRequired;

	@Column(name = "is_user_defined")
	private long isUserDefined;

	@Column(name = "default_value")
	private String defaultValue;

	@Column(name = "is_unique")
	private long isUnique;

	@Column(name = "note")
	private String note;
	
	@Column(name = "is_customized")
	private int isCustomized;
	
	@Column(name = "is_used_in_site")	
	private int isUsedInSite;
	
	@Column(name = "config_type")	
	private int configType;
	
	@Transient
	private String type = "input";

	@Transient
	private String operationType;

	@OneToOne(mappedBy = "eavAttribute", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CatalogEavAttribute catalogEavAttribute;

	@OneToMany(mappedBy = "eavAttribute", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EavAttributeOption> eavAttributeOption = new ArrayList<EavAttributeOption>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
	
	@Column(name = "is_visible")	
	private int isVisible;
	
	@Column(name = "is_seo_key_word")	
	private String isSeoKeyWord;

	public EavAttribute(long attributeId) {
		this.attributeId = attributeId;
	}

	public EavAttribute() {
		super();
	}

	public EavAttribute(String frontendLabel) {
		this.frontendLabel = frontendLabel;
	}

}
