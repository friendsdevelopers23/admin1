package com.calsoft.pos.model.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "customer_entity")
@Data
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_set_id")
	private int attributeSetId;

	@Column(name = "website_id")
	private int webSiteId;

	@Column(name = "email")
	private String email;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "increment_id")
	private String incrementId;

	@Column(name = "store_id")
	private int storeId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "is_active")
	private int active;

	@JsonIgnore
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CustomerVarchar> custVar = new ArrayList<CustomerVarchar>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CustomerInt> customerInt = new ArrayList<CustomerInt>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CustomerText> customerText = new ArrayList<CustomerText>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CustomerDateTime> customerDateTime = new ArrayList<CustomerDateTime>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	@JsonProperty("address")
	private List<CustomerAddressEntity> customerAddress = new ArrayList<CustomerAddressEntity>();

}
