package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calsoft.pos.model.cart.SalesFlatQuoteItemOption;

public interface SalesFlatQuoteItemOptionJpaRepository extends JpaRepository<SalesFlatQuoteItemOption, Integer> {
	
	
	SalesFlatQuoteItemOption findByCode(String code);

}
