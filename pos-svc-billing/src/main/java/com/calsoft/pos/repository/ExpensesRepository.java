package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ExpensesModel;
@Repository
public interface ExpensesRepository
		extends JpaRepository<ExpensesModel, Long>, JpaSpecificationExecutor<ExpensesModel> {
	Page<ExpensesModel> findByTenantId(Pageable pageable, String tenantId);

	ExpensesModel findByIdAndTenantId(long ExpensesId, String tenantId);

	List<ExpensesModel> findByTenantId(String tenantId);

}
