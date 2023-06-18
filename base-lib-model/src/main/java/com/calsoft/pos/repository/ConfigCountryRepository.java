package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CoreConfigCountry;

@Repository
public interface ConfigCountryRepository
		extends JpaRepository<CoreConfigCountry, Long>, JpaSpecificationExecutor<CoreConfigCountry> {

	Page<CoreConfigCountry> findByTenantId(Pageable pageable, String tenantId);

}
