package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.shipment.SalesFlatShipment;
@Repository
public interface SalesFlatShipmentJpaRepository extends JpaRepository<SalesFlatShipment,Long>  {
	
	
	@Query(value="SELECT a FROM SalesFlatShipment a INNER JOIN SalesFlatShipmentItem b ON a.entityId = b.parentId where a.orderId =?1 And b.productId=?2 And a.tenantId = ?3")
	List<SalesFlatShipment> trackShipment(long orderId,long productId, String tenantId);

}
