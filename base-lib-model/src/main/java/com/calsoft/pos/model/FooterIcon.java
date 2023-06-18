package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "footer_icon")
@AllArgsConstructor
public class FooterIcon implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "icon_name")
	private String iconName;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "link")
	private String link;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
	
	@Column(name = "link_navigated")
	private int linkNavigated;
	
	@Column(name = "position")
	private int position;

	@Transient
	private String operationType;

}
