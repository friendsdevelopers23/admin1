package com.calsoft.pos.model.categoryindex;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.calsoft.utils.CommonUtils;

@Entity
public class CategoryDb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTITY_PK")
	private String entityPk;

	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_set_id")
	private int attributeSetId;

	@Column(name = "parent_id")
	private int parentId;

	@Column(name = "created_at", updatable = false)
	private Timestamp createdDate = CommonUtils.getCurrentTimeStamp();

	@Column(name = "updated_at")
	private Timestamp updatedDate = CommonUtils.getCurrentTimeStamp();

	@Column(name = "path")
	private String path;

	@Column(name = "position")
	private int position;

	@Column(name = "level")
	private int level;

	@Column(name = "children_count")
	private int childrenCount;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "frontend_label")
	private String frontendLabel;

	@Column(name = "backend_type")
	private String backendType;

	@Column(name = "value")
	private String value;

	@Column(name = "required")
	private int required;

	@Column(name = "attribute_code")
	private String attributeCode;

	public String getEntityPk() {
		return entityPk;
	}

	public void setEntityPk(String entityPk) {
		this.entityPk = entityPk;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(int entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public int getAttributeSetId() {
		return attributeSetId;
	}

	public void setAttributeSetId(int attributeSetId) {
		this.attributeSetId = attributeSetId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getFrontendLabel() {
		return frontendLabel;
	}

	public void setFrontendLabel(String frontendLabel) {
		this.frontendLabel = frontendLabel;
	}

	public String getBackendType() {
		return backendType;
	}

	public void setBackendType(String backendType) {
		this.backendType = backendType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public CategoryDb() {
		super();
	}

	public CategoryDb(String entityPk, int entityId, int entityTypeId, int attributeSetId, int parentId,
			Timestamp createdDate, Timestamp updatedDate, String path, int position, int level, int childrenCount,
			int attributeId, String frontendLabel, String backendType, String value, int required,
			String attributeCode) {
		super();
		this.entityPk = entityPk;
		this.entityId = entityId;
		this.entityTypeId = entityTypeId;
		this.attributeSetId = attributeSetId;
		this.parentId = parentId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.path = path;
		this.position = position;
		this.level = level;
		this.childrenCount = childrenCount;
		this.attributeId = attributeId;
		this.frontendLabel = frontendLabel;
		this.backendType = backendType;
		this.value = value;
		this.required = required;
		this.attributeCode = attributeCode;
	}

}
