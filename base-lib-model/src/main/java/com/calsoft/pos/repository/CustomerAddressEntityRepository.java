package com.calsoft.pos.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.customer.CustomerAddressEntity;

public interface CustomerAddressEntityRepository extends JpaRepository<CustomerAddressEntity, Long> {

	@Transactional
	@Modifying // to mark delete or update query
	@Query(value = "DELETE FROM CustomerAddressEntity e WHERE e.entityId = ?1")
	void deleteAddressId(int entityId);

}
