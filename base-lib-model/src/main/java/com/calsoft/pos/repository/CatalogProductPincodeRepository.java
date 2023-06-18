package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.product.CatalogProductPincode;

@Repository
public interface CatalogProductPincodeRepository extends JpaRepository<CatalogProductPincode, Long>, JpaSpecificationExecutor<CatalogProductPincode> { 

	List<CatalogProductPincode> findByIdAndProductId(int id, long productId);
	Page<CatalogProductPincode> findByProductId(Pageable pageable, long productId);
	List<CatalogProductPincode> findByPincodeAndProductId(String name, Long productId);
	
	

}
