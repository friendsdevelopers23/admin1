package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long>, JpaSpecificationExecutor<Settings> {
	Page<Settings> findByTenantId(Pageable pageable, String tenantId);

	Settings findBySettingIdAndTenantId(long SettingsId, String tenantId);

	List<Settings> findByTenantId(String tenantId);

}
