package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.creditmemo.SalesFlatCreditmemo;
@Repository
public interface SalesFlatCreditmemoJpaRepository  extends JpaRepository<SalesFlatCreditmemo, Long> {

}
