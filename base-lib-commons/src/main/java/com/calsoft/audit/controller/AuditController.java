//package com.calsoft.audit.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
//
//import com.calsoft.audit.service.AuditService;
//import com.calsoft.pos.model.ResponseWrapper;
//import com.calsoft.pos.model.utils.ResponseCodes02;
//import com.calsoft.utils.CommonUtils;
//
//@RequestMapping("/api")
//@RestController
//public class AuditController {
//
//	@Autowired
//	public AuditService auditService;
//	Logger logger = LoggerFactory.getLogger(AuditController.class);
//
//	public ResponseEntity<ResponseWrapper> constructResponseEntity(Object result, String statusCode,
//			String statusMessage) {
//		ResponseWrapper response = new ResponseWrapper(result, statusCode, statusMessage);
//		ResponseEntity<ResponseWrapper> responseEntity = new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
//		return responseEntity;
//	}
//
//	/***
//	 * this method using audit data for given entityId
//	 * 
//	 * @param entityId
//	 * @return
//	 */
//
//	@GetMapping("/audit/{auditType}/{entityId}")
//	public ResponseWrapper findAuditByEntityId(@PathVariable(value = "auditType") String auditType,
//			@PathVariable(value = "entityId") String entityId) {
//		final List auditData = auditService.getAuditData(auditType, Integer.parseInt(entityId));
//
//		return new ResponseWrapper(auditData, ResponseCodes02.RECORD_FETCH_SUCCESS.getCode(),
//				ResponseCodes02.RECORD_FETCH_SUCCESS.getDescription());
//
//	}
//
//	
//	@GetMapping("/audit/export/{auditType}/{entityId}")
//	public StreamingResponseBody shopcartReport(
//			@PathVariable(value = "auditType") String auditType, @PathVariable(value = "entityId") String entityId,HttpServletResponse response) {
//		StreamingResponseBody outputStream = null;
//		try {
//			response.setContentType("application/vnd.ms-excel");
//			String downLoadFileName = "audit_" + CommonUtils.getCurrentTimeStamp() + ".xls";
//			response.setHeader("Content-Disposition", "attachment; filename=" + downLoadFileName);
//			response.setHeader("filename", downLoadFileName);
//			final byte[] bytes = auditService.generateAuditReport(auditType, entityId);
//			if (bytes != null && bytes.length > 0) {
//				logger.info("Report generated successfully...");
//				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//				outputStream = getOutputStream(byteArrayInputStream);
//			} else {
//				logger.info("Report Generation failed ");
//			}
//		} catch (Exception exp) {
//			logger.error("Exception while trying to generate report ", exp);
//			constructResponseEntity(false, ResponseCodes02.REPORT_FAILED.getCode(),
//					ResponseCodes02.REPORT_FAILED.getDescription());
//
//		}
//		return outputStream;
//	}
//	private StreamingResponseBody getOutputStream(InputStream inputStream2) {
//		return outputStream -> {
//			int nRead;
//			byte[] data = new byte[1024];
//			while ((nRead = inputStream2.read(data, 0, data.length)) != -1) {
//				outputStream.write(data, 0, nRead);
//			}
//
//		};
//	}
//
//}
