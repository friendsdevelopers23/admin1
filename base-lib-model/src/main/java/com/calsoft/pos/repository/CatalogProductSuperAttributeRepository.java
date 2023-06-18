package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.product.CatalogProductSuperAttribute;
import com.calsoft.pos.model.product.CatalogProductSuperAttributeLabel;

public interface CatalogProductSuperAttributeRepository extends JpaRepository<CatalogProductSuperAttribute, Long> {

	List<CatalogProductSuperAttribute> findByProductId(long productId);
	
	List<CatalogProductSuperAttribute> findByProductIdAndCatalogProductSuperAttributeLabelValue(long productId,String value);
	
	@Transactional
	@Modifying // to mark delete or update query
	@Query(value = "DELETE FROM CatalogProductSuperAttribute e WHERE e.productId = ?1")
	void deleteByProductId(long entityId);
}
