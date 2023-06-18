package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "core_translate")
@AllArgsConstructor
public class CoreTranslate implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "key_id")
	private int keyId;

	@Column(name = "string")
	private String value;

	@Column(name = "store_id")
	private int store_id;

	@Column(name = "translate")
	private String translate;

	@Column(name = "locale")
	private String locale;

	@Column(name = "crc_string")
	private String crc_string;

}
