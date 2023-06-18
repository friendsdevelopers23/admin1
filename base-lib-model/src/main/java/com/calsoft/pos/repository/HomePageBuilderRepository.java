package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.HomePageBuilder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface HomePageBuilderRepository extends JpaRepository<HomePageBuilder, Long> {

	@Query(value = "SELECT e from HomePageBuilder e WHERE e.tenantId = ?1 order by e.position asc")
	Page<HomePageBuilder> findByTenantId(Pageable pageable, String tenantId);
	
	
	@Query(value = "SELECT e from HomePageBuilder e WHERE e.tenantId = ?1 and e.pageType=?2 order by e.position asc")
	Page<HomePageBuilder> findByTenantIdDetails(Pageable pageable, String tenantId, int pageType);

	List<HomePageBuilder> findByTenantIdAndPageType(String tenantId, int pageType);

	@Modifying 
	@Query(value = "DELETE FROM HomePageBuilder e WHERE e.tenantId = ?1")
	void deleteByTenantId(String tenantId);

	Page<HomePageBuilder> findByTenantIdAndPageType(Pageable pageable, String tenantId, int pageType);

}
