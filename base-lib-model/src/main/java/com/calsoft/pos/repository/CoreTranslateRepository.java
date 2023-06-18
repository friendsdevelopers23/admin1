
package com.calsoft.pos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.CoreTranslate;

@Repository
public interface CoreTranslateRepository extends JpaRepository<CoreTranslate, Long>,JpaSpecificationExecutor<CoreTranslate> {

	Page<CoreTranslate> findByValueAndLocale(String value, String locale, Pageable pageable);

	CoreTranslate findByKeyId(int keyId);

}
