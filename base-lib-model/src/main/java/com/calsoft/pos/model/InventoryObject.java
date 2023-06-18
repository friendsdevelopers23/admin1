package com.calsoft.pos.model;

import lombok.Data;

@Data
public class InventoryObject {
	
	private long productId;
	
	private double qty;
	
	private String tenanatId;
}
