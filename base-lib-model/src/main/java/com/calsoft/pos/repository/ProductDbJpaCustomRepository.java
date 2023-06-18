package com.calsoft.pos.repository;

import java.util.List;

import com.calsoft.pos.model.productindex.ProductDb;
public interface ProductDbJpaCustomRepository  {
	
List<ProductDb> findbyProductId(Long productId, String tenantId) throws Exception   ;
List<ProductDb> findbyProductIdWithOutAdditionValue(Long productId,String tenantId) throws Exception;
}
