package com.calsoft.pos.model.medical;

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
@Table(name = "medical_delivery_option")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDeliveryOption implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "visibility")
	private int visibility;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "free_consultation")
	private int freeConsultation;

	@Column(name = "consultation_fees")
	private double consultationFees;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private int type;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
