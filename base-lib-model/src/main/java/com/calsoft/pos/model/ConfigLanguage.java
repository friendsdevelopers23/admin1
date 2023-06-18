package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "core_language_config")
public class ConfigLanguage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "Locale")
	private String locale;

	@Column(name = "Language_code")
	private String languageCode;

	@Column(name = "LCID_string")
	private String lcidString;

	@Column(name = "LCID_Decimal")
	private String lcidDecimal;

	@Column(name = "LCID_Hexadecimal")
	private String lcidHexadecimal;

	@Column(name = "is_active")
	private int isActive;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
