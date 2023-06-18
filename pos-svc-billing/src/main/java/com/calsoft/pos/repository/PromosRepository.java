package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Promos;

@Repository
public interface PromosRepository extends JpaRepository<Promos, Long>, JpaSpecificationExecutor<Promos> {
	Page<Promos> findByTenantId(Pageable pageable, String tenantId);

	Promos findByIdAndTenantId(long PromosId, String tenantId);

	List<Promos> findByTenantId(String tenantId);

}
