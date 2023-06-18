package com.calsoft.pos.model.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditVO implements Serializable {
    private String fieldName;
    private String fieldCurrentValue;
    private String fieldPreviousValue;
    private String lastUpdatedDate;
    private String lastUpdatedUser;
    private String updatedVia;
}
