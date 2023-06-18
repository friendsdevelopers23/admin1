package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "sales_order_status_state")
@Data
public class SalesOrderStatusState implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
    
	@Id
	@Column(name = "status")
	public String status;

	@Column(name = "state")
	public String state;

	@Column(name = "is_default")
	private double isDefault;

	@Transient
	private String operationType;

	
}
