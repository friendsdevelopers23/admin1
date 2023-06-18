package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Variants;

@Repository
public interface VariantsRepository extends JpaRepository<Variants, Long>, JpaSpecificationExecutor<Variants> {
	Page<Variants> findByTenantId(Pageable pageable, String tenantId);

	Variants findByIdAndTenantId(long VariantsId, String tenantId);

	List<Variants> findByTenantId(String tenantId);

}
