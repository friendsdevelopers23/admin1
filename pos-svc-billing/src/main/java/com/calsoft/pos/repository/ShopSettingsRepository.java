package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ShopSettings;

@Repository
public interface ShopSettingsRepository
		extends JpaRepository<ShopSettings, Long>, JpaSpecificationExecutor<ShopSettings> {

	Page<ShopSettings> findByTenantId(Pageable pageable, String tenantId);

	ShopSettings findByShopIdAndTenantId(long shopId, String tenantId);

	List<ShopSettings> findByTenantId(String tenantId);

}
