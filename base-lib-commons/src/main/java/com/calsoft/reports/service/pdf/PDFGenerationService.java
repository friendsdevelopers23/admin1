package com.calsoft.reports.service.pdf;

import com.calsoft.reports.service.ReportService;
import com.calsoft.reports.vo.ReportVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;

@Service("pdf")
public class PDFGenerationService implements ReportService {
    Logger logger = LoggerFactory.getLogger(PDFGenerationService.class);

    public byte[] generate(ReportVO reportVO) {
        try {
            final JasperReport jasperReport = initializeReport(reportVO);
            final JasperPrint jasperPrint = generateReportTemplate(jasperReport, reportVO.getBeanColl());
            return exportPDF(jasperPrint);
        } catch (Exception e) {
        logger.error("Exception while trying to generate  :: ", e);
    }
        return null;
    }

    @Override
    public byte[] downloadTemplateFile(String filename) {
        return null;
    }

    @Override
    public List<Map<String, String>> uploadFile(MultipartFile newsletterFile, Map<String, String> newsLetterFieldMap){return null;};



    private byte[] exportPDF(JasperPrint jasperPrint) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JRPdfExporter jrPdfExporter = new JRPdfExporter();
            ArrayList<ExporterInputItem> items = new ArrayList<>();
            items.add(new SimpleExporterInputItem(jasperPrint));
            jrPdfExporter.setExporterInput(new SimpleExporterInput(items));
            jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            jrPdfExporter.exportReport();
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Exception while exporting pdf :: ", e);
        } catch (JRException e) {
            logger.error("Exception while exporting pdf :: ", e);
        }
        return null;
    }

    private JasperPrint generateReportTemplate(JasperReport jasperReport, Collection<?> beanColl) {
        try {
            logger.info("Generating Report Template :: {} ", beanColl.toString());
            Map<String, Object> params = null;
            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,  new JRBeanCollectionDataSource(beanColl));
            return jasperPrint;
        } catch (Exception e) {
            logger.error("Exception while trying to generate Reporting template :: ", e);
        }
        return null;
    }

    private JasperReport initializeReport(ReportVO reportVO) {
        logger.info("Initializing PDF Report :: {} ", reportVO.toString());
        String jrxmlName = reportVO.getTemplateId().getTemplateName();
        try {
           return compileReport(PDFGenerationService.class.getClassLoader().getResourceAsStream(reportVO.getTemplateId().getTemplateName()));
        } catch (Exception e) {
            logger.error("Exception while trying to compile JRXML :: " + jrxmlName, e);
        }
        return null;
    }

	@Override
	public boolean verifyTemplate(Map<String, String> pincodeColMap, Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map<String, String>> uploadFileNewExcel(MultipartFile newsletterFile,
			Map<String, String> newsLetterFieldMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
