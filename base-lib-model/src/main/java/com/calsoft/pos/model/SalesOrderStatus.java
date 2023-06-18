package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_order_status")
public class SalesOrderStatus implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@Column(name = "status")
	public String status;

	@Column(name = "label")
	public String label;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
