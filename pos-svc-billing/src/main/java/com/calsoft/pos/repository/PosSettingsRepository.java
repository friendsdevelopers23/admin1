package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.PosSettings;

@Repository
public interface PosSettingsRepository extends JpaRepository<PosSettings, Long>, JpaSpecificationExecutor<PosSettings> {
	Page<PosSettings> findByTenantId(Pageable pageable, String tenantId);

	PosSettings findByPosIdAndTenantId(long SettingsId, String tenantId);

	List<PosSettings> findByTenantId(String tenantId);

}

