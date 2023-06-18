package com.calsoft.pos.repository;

import java.util.List;

import com.calsoft.pos.model.categoryindex.CategoryDb;

public interface CategoryDbJpaCustomRepository {

	List<CategoryDb> findbyCategoryId(Integer valueOf, String tentantId) throws Exception;

}
