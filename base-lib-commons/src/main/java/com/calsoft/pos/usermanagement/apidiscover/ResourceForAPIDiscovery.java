package com.calsoft.pos.usermanagement.apidiscover;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resource")
public class ResourceForAPIDiscovery
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = ModuleTypeConverter.class)
	@Column(name = "RESOURCE_TYPE")
	private ResourceType resourceType;

	@Column(name = "MODULE")
	private String module;

	@Column(name = "PATH")
	private String path;

	@Column(name = "METHOD")
	private String method;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "CREATED_DATE",insertable = true,updatable = false)
	private Timestamp createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE",insertable = false,updatable = true)
	private Timestamp updatedDate;

	
	@Transient
	@JsonProperty("subModules")
	private  List<ResourceForAPIDiscovery> subModules = new ArrayList<ResourceForAPIDiscovery>();

	public ResourceForAPIDiscovery(String module) {
		this.module = module;
	}
	

	

}