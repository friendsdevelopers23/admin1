//package com.calsoft.audit.service;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import org.javers.core.Javers;
//import org.javers.core.JaversBuilder;
//import org.javers.core.diff.Diff;
//import org.javers.core.diff.changetype.ValueChange;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.calsoft.pos.model.audit.AuditData;
//import com.calsoft.pos.model.audit.AuditVO;
//import com.calsoft.pos.repository.AuditDataRepository;
//import com.calsoft.reports.service.ReportService;
//import com.calsoft.reports.service.excel.WorkSheetContent;
//import com.calsoft.reports.vo.ReportVO;
//import com.calsoft.reports.vo.TableHeader;
//import com.calsoft.utils.ExcelUtils;
//import com.calsoft.utils.RestUtils;
//
//@Service
//public class AuditService<K> {
//	Logger logger = LoggerFactory.getLogger(AuditService.class);
//	String[] ignoreList = { "updatedDate", "userUpdated" };
//	@Autowired
//	AuditDataRepository auditRepository;
//
//	@Qualifier("excel")
//	@Autowired
//	private ReportService reportService;
//
//	public void saveAudit(K input, Object pkField) {
//		String typ = input.getClass().getName();
//		logger.info("Type : {} ", typ);
//		AuditData audiData = new AuditData();
//		String auditData = RestUtils.toJSON(input);
//		audiData.setAuditJSON(auditData);
//		audiData.setAuditType(typ);
//		final Object pkVal = callGetter(input, pkField);
//		logger.info("PK Field : {}  PK Value : {} ", pkField, pkVal);
//		audiData.setEntityId(Long.valueOf((String) pkVal));
//		audiData.setDataVersion(getRevisionIdByEntity(pkVal));
//		auditRepository.save(audiData);
//	}
//
//	private int getRevisionIdByEntity(Object pkVal) {
//		Integer revisionByEntityId = auditRepository.findRevisionByEntityId(Long.valueOf((String) pkVal));
//		logger.info("Revision Id :: {} ", revisionByEntityId);
//		if (revisionByEntityId == null)
//			return 1;
//		return revisionByEntityId + 1;
//	}
//
//	public static Object callGetter(Object obj, Object fieldName) {
//		PropertyDescriptor pd;
//		try {
//			pd = new PropertyDescriptor(String.valueOf(fieldName), obj.getClass());
//			final Object fieldValObj = pd.getReadMethod().invoke(obj);
//			return fieldValObj.toString();
//		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//		}
//		return null;
//
//	}
//
//	public List<AuditVO> getAuditData(String auditType, long entityId) {
//
//		try {
//			final List<AuditData> audData = auditRepository.findByAuditTypeAndEntityIdOrderByDateUpdatedDesc(auditType,
//					entityId);
//			if (audData.size() > 1) {
//				List<AuditVO> auditVOList = new ArrayList<AuditVO>();
//				parseAuditData(audData, auditVOList);
//				return auditVOList;
//			}
//		} catch (Exception auditdataexp) {
//			logger.error("Exception while trying to retrieve audit :: ", auditdataexp);
//		}
//		return null;
//	}
//
//	private void parseAuditData(List<AuditData> audData, List<AuditVO> auditVOList) throws ClassNotFoundException {
//		// given
//		Javers javers = JaversBuilder.javers().build();
//		for (int i = 0; i < (audData.size() - 1); i++) {
//			// when
//			final AuditData auditData00 = audData.get(i);
//			final AuditData auditData01 = audData.get(i + 1);
//			final Object obj00 = RestUtils.parseJSON(auditData00.getAuditJSON(),
//					Class.forName(auditData00.getAuditType()));
//			final Object obj01 = RestUtils.parseJSON(auditData01.getAuditJSON(),
//					Class.forName(auditData00.getAuditType()));
//			Diff diff = javers.compare(obj00, obj01);
//
//			// then
//			List<ValueChange> changes = diff.getChangesByType(ValueChange.class);
//			final int cSize = diff.getChanges().size();
//			logger.info("Number of Changes : {} ", cSize);
//			if (cSize > 0) {
//				changes.stream().forEach(valueChange -> transformToAuditVO(auditVOList, valueChange, auditData01));
//			}
//		}
//	}
//
//	private void transformToAuditVO(List<AuditVO> auditVOList, ValueChange valueChange, AuditData auditData01) {
//		String propertyName = valueChange.getPropertyName();
//		if (!Arrays.stream(ignoreList).parallel().anyMatch(propertyName::contains)) {
//			auditVOList.add(new AuditVO(propertyName, String.valueOf(valueChange.getLeft()),
//					String.valueOf(valueChange.getRight()), auditData01.getDateUpdated().toString(),
//					auditData01.getUserUpdated(), auditData01.getUpdateVia()));
//		}
//	}
//
//	public byte[] generateAuditReport(String auditType, String entityId) {
//		List<AuditVO> auditVO = getAuditData(auditType, Long.valueOf(entityId));
//		List<String> columnList = Arrays.asList(ExcelUtils.AUDIT_TABLE_COL_NAMES);
//		List<TableHeader> tableHeaders = ExcelUtils.getExcelHeaderContents(columnList, ExcelUtils.getAuditTableColMap(),
//				ExcelUtils.getAuditTableWidthMap());
//		List<Map<String, String>> excelContents = ExcelUtils.getExcelAuditContents(columnList, auditVO,
//				ExcelUtils.getAuditTableFieldMap(), ExcelUtils.EXCEL_DATEFORMAT);
//		List<WorkSheetContent> excelWorkSheetContents = ExcelUtils.getExcelWorksheetContents(
//				ExcelUtils.AUDIT_TABLE_SHEET_NAME, ExcelUtils.AUDIT_TABLE_SHEET_NAME, tableHeaders, excelContents);
//		ReportVO reportVO = new ReportVO();
//		reportVO.setWorkSheetContentList(excelWorkSheetContents);
//		byte[] excelStream = reportService.generate(reportVO);
//		return excelStream;
//	}
//
//}
