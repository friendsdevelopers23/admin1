package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.product.CatalogInventoryStockStatus;


@Repository
public interface CatalogInventoryStockStatusRepository extends JpaRepository<CatalogInventoryStockStatus, Long> {

	CatalogInventoryStockStatus findByProductId(long productId);
}
