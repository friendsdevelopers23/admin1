package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.ExpenseCategories;

@Repository
public interface ExpenseCategoriesRepository
		extends JpaRepository<ExpenseCategories, Long>, JpaSpecificationExecutor<ExpenseCategories> {
	Page<ExpenseCategories> findByTenantId(Pageable pageable, String tenantId);

	ExpenseCategories findByIdAndTenantId(long ExpenseCategoriesId, String tenantId);

	List<ExpenseCategories> findByTenantId(String tenantId);

}
