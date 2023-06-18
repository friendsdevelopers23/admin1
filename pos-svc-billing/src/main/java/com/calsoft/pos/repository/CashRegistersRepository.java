
package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CashRegister;

@Repository
public interface CashRegistersRepository
		extends JpaRepository<CashRegister, Long>, JpaSpecificationExecutor<CashRegister> {

	CashRegister findByIdAndTenantId(int CashRegistersId, String tenantId);

	Page<CashRegister> findByTenantId(Pageable pageable, String tenantId);

	CashRegister findByUserIdAndStatusAndTenantId(Long userId, int status, String tenantId);

}
