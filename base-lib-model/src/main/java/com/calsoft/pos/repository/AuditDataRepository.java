package com.calsoft.pos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.audit.AuditData;

import java.util.List;


@Repository
public interface AuditDataRepository extends CrudRepository<AuditData, Long> {
    @Query("select max(a.dataVersion) from AuditData a")
    public Integer findRevisionByEntityId(Long entityId);
    public List<AuditData>  findByAuditTypeAndEntityIdOrderByDateUpdatedDesc(String auditType,Long entityId);
}
