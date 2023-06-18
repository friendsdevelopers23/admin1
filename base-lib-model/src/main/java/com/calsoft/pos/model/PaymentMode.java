package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_mode")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "description")
	private String description;

	@Column(name = "type", updatable = false)
	private String type;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Column(name = "minimum_value")
	private double minimumValue;
		
	@Column(name = "upi_id")
	private String upiId;
	
	@Column(name = "qrcode_image")
	private String qrcodeImage;

}
