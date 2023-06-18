package com.calsoft.pos.controller.units;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calsoft.pos.service.BarcodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api")
public class BarcodeController {
	@Autowired
	BarcodeService barcodeService;

	public BarcodeController(BarcodeService barcodeService) {
		this.barcodeService = barcodeService;
	}

	@GetMapping("/barcode")
	public ResponseEntity<byte[]> generateBarcode(@RequestParam String code,
			@RequestParam(required = false, defaultValue = "QR_CODE") BarcodeFormat format,
			@RequestParam(required = false, defaultValue = "200") int width,
			@RequestParam(required = false, defaultValue = "200") int height) throws WriterException, IOException {
		byte[] image = barcodeService.generateBarcode(code, format, width, height);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return ResponseEntity.ok().headers(headers).body(image);
	}
}