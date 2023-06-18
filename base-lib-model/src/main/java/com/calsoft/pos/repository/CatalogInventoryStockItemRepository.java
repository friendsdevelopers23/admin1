
package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.product.CatalogInventoryStockItem;

@Repository
public interface CatalogInventoryStockItemRepository extends JpaRepository<CatalogInventoryStockItem, Long> {

	CatalogInventoryStockItem findByProductId(long productId);
}
