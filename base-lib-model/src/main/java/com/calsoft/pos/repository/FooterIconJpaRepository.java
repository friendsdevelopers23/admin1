package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.FooterIcon;

@Repository
public interface FooterIconJpaRepository  extends JpaRepository<FooterIcon, Long> ,JpaSpecificationExecutor<FooterIcon> {

	
	Page<FooterIcon> findByTenantId(Pageable pageable, String tenantId);

	public FooterIcon findByIdAndTenantId(Long id, String tenantId);

	List<FooterIcon> findByIconNameAndTenantId(String title, String tenantId);
	
	List<FooterIcon> findByTenantId( String tenantId);
	
	@Query(value = "SELECT e from FooterIcon e WHERE e.tenantId = ?1 and e.isActive=?2 order by e.position asc")
	List<FooterIcon> findByTenantIdAndIsActive( String tenantId,int isActive);
	
	FooterIcon findByTenantIdAndIconName(String tenantId,String title);


}