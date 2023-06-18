package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ConfigLanguage;

@Repository
public interface ConfigLanguageRepository
		extends JpaRepository<ConfigLanguage, Long>, JpaSpecificationExecutor<ConfigLanguage> {

	Page<ConfigLanguage> findByTenantId(Pageable pageable, String tenantId);

	Page<ConfigLanguage> findByTenantIdAndIsActive(String tenantId, int isActive, Pageable pageable);

	ConfigLanguage findByLocaleAndTenantId(String locale, String tenantId);

	List<ConfigLanguage> findByTenantId(String tenantId);

	List<ConfigLanguage> findByTenantIdAndIsActive(String tenantId, int i);

}
