
package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calsoft.pos.model.cart.SalesFlatQuoteAddress;


public interface SalesFlatQuoteAddressJpaRepository extends JpaRepository<SalesFlatQuoteAddress, Long> {

	public SalesFlatQuoteAddress findByAddressId(long addressId);
}
