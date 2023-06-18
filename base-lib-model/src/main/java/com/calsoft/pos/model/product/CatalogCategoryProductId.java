package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CatalogCategoryProductId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long categoryId;

	private Long productId;

}
