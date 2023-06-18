package com.calsoft.pos.model.shipment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_shipment_track")
@Data
public class SalesFlatShipmentTrack implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "qty")
	private Double qty;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "track_number")
	private String trackNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@Column(name = "carrier_code")
	private String carrierCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SalesFlatShipment salesFlatShipment;

}
