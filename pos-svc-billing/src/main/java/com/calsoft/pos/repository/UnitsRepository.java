
package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.Units;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Long>, JpaSpecificationExecutor<Units> {

	Units findByIdAndTenantId (int UnitsId,String tenantId);
	
	Page<Units> findByTenantId(Pageable pageable, String tenantId);

}
