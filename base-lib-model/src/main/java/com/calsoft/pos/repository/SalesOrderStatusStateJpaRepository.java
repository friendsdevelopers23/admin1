package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.SalesOrderStatusState;

@Repository
public interface SalesOrderStatusStateJpaRepository
		extends JpaRepository<SalesOrderStatusState, Long>, JpaSpecificationExecutor<SalesOrderStatusState> {

	SalesOrderStatusState findBystatus(String status);

}
