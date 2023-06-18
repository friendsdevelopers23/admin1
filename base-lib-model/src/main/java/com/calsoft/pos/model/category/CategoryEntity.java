package com.calsoft.pos.model.category;

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

import lombok.Data;

@Entity
@Table(name = "catalog_category_entity")
@Data
public class CategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_set_id")
	private int attributeSetId;

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

	@Column(name = "path")
	private String path;

	@Column(name = "position")
	private int position;

	@Column(name = "level")
	private int level;

	@Column(name = "children_count")
	private int childrenCount;
	
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CategoryVarchar> categoryVarchar = new ArrayList<CategoryVarchar>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CategoryInt> categoryInt = new ArrayList<CategoryInt>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CategoryText> categoryText = new ArrayList<CategoryText>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CategoryDecimal> categoryDecimal = new ArrayList<CategoryDecimal>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<CategoryDateTime> categoryDateTime = new ArrayList<CategoryDateTime>();

}
