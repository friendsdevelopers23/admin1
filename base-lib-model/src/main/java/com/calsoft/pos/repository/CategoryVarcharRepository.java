package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.category.CategoryVarchar;

public interface CategoryVarcharRepository extends JpaRepository<CategoryVarchar, Long>  {

	
	@Query(value="SELECT new com.calsoft.pos.model.category.CategoryVarchar(a.value,a.entityId) FROM CategoryVarchar a INNER JOIN CategoryEntity b ON a.entityId=b.entityId where a.entityId IN (?1) And a.attributeId=?2 And a.entityTypeId=?3 And b.tenantId = ?4")
	List<CategoryVarchar> findCategoryName(List<Integer> path,Integer attributeCode,Integer entityTypeId,String tenantId);
}
