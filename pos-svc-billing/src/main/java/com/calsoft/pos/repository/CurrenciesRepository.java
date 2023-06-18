package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Currencies;
@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Long>, JpaSpecificationExecutor<Currencies> {
	Page<Currencies> findByTenantId(Pageable pageable, String tenantId);

	Currencies findByIdAndTenantId(long CurrenciesId, String tenantId);

	List<Currencies> findByTenantId(String tenantId);

}
