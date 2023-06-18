package com.calsoft.pos.model.product;

import java.util.List;

import lombok.Data;

@Data
public class CatalogProductLinkSearch {

	private String searchterm;
	
	private List<Long> productIds;
	

	
}
