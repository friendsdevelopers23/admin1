package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.product.CatalogProductSuperLink;

public interface CatalogProductSuperLinkRepository extends JpaRepository<CatalogProductSuperLink, Long> {

	List<CatalogProductSuperLink> findByParentId(long productId);
	
	List<CatalogProductSuperLink> findByProductId(long productId);

	@Transactional
	@Modifying // to mark delete or update query
	@Query(value = "DELETE FROM CatalogProductSuperLink e WHERE e.linkId = ?1")
	void deleteByLinkId(long entityId);
}
