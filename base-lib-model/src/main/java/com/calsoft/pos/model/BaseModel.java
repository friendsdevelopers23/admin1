package com.calsoft.pos.model;

import javax.persistence.*;

import com.calsoft.utils.CommonUtils;

import lombok.Data;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseModel {
	@Column(name = "D_CREAT")
	protected Timestamp dateCreated = CommonUtils.getCurrentTimeStamp();

	@Column(name = "X_CREAT")
	protected String userCreated = CommonUtils.getUserName();

	@Column(name = "D_UPD")
	protected Timestamp dateUpdated = CommonUtils.getCurrentTimeStamp();

	@Column(name = "X_UPD")
	protected String userUpdated = CommonUtils.getUserName();
	
	@Column(name = "upd_via")
	protected String updateVia = CommonUtils.getUserAgent().get("os");

	@Transient
	private long status;
	
	@Transient
	protected String operationType = OperationTypeEnum.NOCHANGE.toString();

}
