package com.calsoft.pos.model.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "customer_address_entity")
@Data
public class CustomerAddressEntity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "entity_type_id")
	private int entityTypeId = 2;

	@Column(name = "attribute_set_id")
	private int attributeSetId = 0;

	@Column(name = "increment_id")
	private String incrementId;

	@Column(name = "parent_id")
	private int parentId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "is_active")
	private int active = 1;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	private List<CustomerAddressVarchar> custVar = new ArrayList<CustomerAddressVarchar>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	private List<CustomerAddressInt> customerInt = new ArrayList<CustomerAddressInt>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	private List<CustomerAddressText> customerText = new ArrayList<CustomerAddressText>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	private List<CustomerAddressDateTime> customerDateTime = new ArrayList<CustomerAddressDateTime>();

}
