package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Restriction;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Long>, JpaSpecificationExecutor<Restriction> {

	List<Restriction> findByPlanId(long planId);

}
