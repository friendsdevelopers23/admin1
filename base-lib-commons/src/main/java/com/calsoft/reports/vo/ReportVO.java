package com.calsoft.reports.vo;
import com.calsoft.reports.service.excel.WorkSheetContent;
import com.calsoft.reports.util.ReportTemplate;
import com.calsoft.reports.util.ReportType;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Data
@ToString
public class ReportVO {

    private ReportType reportType;
    private Collection<?> beanColl;
    ReportTemplate templateId;
    List<WorkSheetContent> workSheetContentList;
    private String downloadTemplateFile;
}
