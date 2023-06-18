package com.calsoft.pos.model.audit;


import com.calsoft.pos.model.BaseModel;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "audit_data")
@TypeDef(
		name = "json",
		typeClass = JsonStringType.class
)
public class AuditData extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "audit_id")
	private long auditId;

	@Column(name="entity_id")
	private long entityId;

	@Column(name="data_version")
	private int dataVersion;

	@Column(name = "audit_type")
	private String auditType;

	@Type(type = "json")
	@Column(name = "audit_json")
	private String auditJSON;
	


}
