package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.calsoft.pos.model.Account;


public interface AccountJpaRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

	Account findByDomain(String domain);
	
	Account findByAccountName(String accountName);
	
	List<Account> findByTenantId(String tenantId);

}
