package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.product.CatalogProductLink;

@Repository
public interface CatalogProductLinkRepository extends JpaRepository<CatalogProductLink, Long> {

	@Query("select c.linkedProductId from CatalogProductLink c where c.linkTypeId=?1 And c.productId=?2")
	List<Long> findByLinkTypeIdAndProductId(int linkTypeId, Long productId);
	
	List<CatalogProductLink> findByProductId(Long productId);

}
