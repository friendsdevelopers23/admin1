package com.calsoft.reports.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.calsoft.reports.vo.ReportVO;

public interface ReportService {
    public byte[] generate(ReportVO reportVO);

    public  byte[] downloadTemplateFile(String filename);

    public List<Map<String, String>> uploadFile(MultipartFile newsletterFile, Map<String, String> newsLetterFieldMap);

    public boolean verifyTemplate(Map<String, String> pincodeColMap, Map<String, String> map);

    public List<Map<String, String>> uploadFileNewExcel(MultipartFile newsletterFile, Map<String, String> newsLetterFieldMap);
}
