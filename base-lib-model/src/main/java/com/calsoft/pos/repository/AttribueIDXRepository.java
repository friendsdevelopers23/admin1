package com.calsoft.pos.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.AttribueIDX;


@Repository
public interface AttribueIDXRepository extends SolrCrudRepository<AttribueIDX, Long>{
	
	List<AttribueIDX> findByCategoryId(long entityId);
}
