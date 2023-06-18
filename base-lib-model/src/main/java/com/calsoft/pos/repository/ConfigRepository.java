package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CoreConfigData;

@Repository
public interface ConfigRepository extends JpaRepository<CoreConfigData, Long> {

	CoreConfigData findByTenantId(String tenantId);

	@Query(value = "SELECT new  com.calsoft.pos.model.CoreConfigData(u.layout,u.color) FROM CoreConfigData u where u.tenantId=?1")
	public CoreConfigData findTheme(String tenantId);

	@Query(value = "SELECT u FROM CoreConfigData u where tenant_id=?1")
	public CoreConfigData getAllDetails(String tenantId);

	@Query(value = "SELECT new  com.calsoft.pos.model.CoreConfigData(u.siteName,u.tenantId,u.removeExpiryProductFromStock) FROM CoreConfigData u")
	public List<CoreConfigData> findAllTenant();

}
