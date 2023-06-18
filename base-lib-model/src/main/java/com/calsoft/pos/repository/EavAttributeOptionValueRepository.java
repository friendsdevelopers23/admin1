package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calsoft.pos.model.eavattribute.EavAttributeOptionValue;

public interface EavAttributeOptionValueRepository extends JpaRepository<EavAttributeOptionValue, Long> {

	@Query(value = "SELECT new  com.calsoft.pos.model.eavattribute.EavAttributeOptionValue(u.optionId,u.value) FROM EavAttributeOptionValue u where u.optionId in ?1 group by u.optionId order by u.valueId asc")
	List<EavAttributeOptionValue> findByCorrespondingValueById(List<Long> optionIdlist);
	
	@Query(value = "SELECT new  com.calsoft.pos.model.eavattribute.EavAttributeOptionValue(u.valueId,u.optionId,u.value) FROM EavAttributeOptionValue u LEFT JOIN EavAttributeOption b ON u.optionId=b.optionId LEFT JOIN EavAttribute c ON c.attributeId=b.attributeId  where u.value in ?1 and c.attributeId=?2  group by u.value")
	List<EavAttributeOptionValue> findByCorrespondingValueByIdAndAttributeId(String[] value,long attributeId);
}
