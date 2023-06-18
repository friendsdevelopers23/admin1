package com.calsoft.pos.model.customerindex;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.calsoft.utils.CommonUtils;


@Entity

public class CustomerDb implements Serializable {

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

	@Column(name = "website_id")
	private int websiteId;

	@Column(name = "email")
	private String email;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "increment_id")
	private Integer incrementId;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "created_at", updatable = false)
	private Timestamp createdDate = CommonUtils.getCurrentTimeStamp();

	@Column(name = "updated_at")
	private Timestamp updatedDate = CommonUtils.getCurrentTimeStamp();

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "disable_auto_group_change")
	private long disableAutoGroupChange;

	@Column(name = "attribute_code")
	private String attributeCode;

	@Column(name = "frontend_label")
	private String frontendLabel;

	@Column(name = "backend_type")
	private String backendType;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "value")
	private String value;

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

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Integer getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(Integer incrementId) {
		this.incrementId = incrementId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getDisableAutoGroupChange() {
		return disableAutoGroupChange;
	}

	public void setDisableAutoGroupChange(long disableAutoGroupChange) {
		this.disableAutoGroupChange = disableAutoGroupChange;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
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

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CustomerDb(String entityPk, int entityId, int entityTypeId, int attributeSetId, int websiteId, String email,
			int groupId, Integer incrementId, int storeId, Timestamp createdDate, Timestamp updatedDate, int isActive,
			long disableAutoGroupChange, String attributeCode, String frontendLabel, String backendType,
			int attributeId, String value) {
		super();
		this.entityPk = entityPk;
		this.entityId = entityId;
		this.entityTypeId = entityTypeId;
		this.attributeSetId = attributeSetId;
		this.websiteId = websiteId;
		this.email = email;
		this.groupId = groupId;
		this.incrementId = incrementId;
		this.storeId = storeId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isActive = isActive;
		this.disableAutoGroupChange = disableAutoGroupChange;
		this.attributeCode = attributeCode;
		this.frontendLabel = frontendLabel;
		this.backendType = backendType;
		this.attributeId = attributeId;
		this.value = value;
	}

	

}
