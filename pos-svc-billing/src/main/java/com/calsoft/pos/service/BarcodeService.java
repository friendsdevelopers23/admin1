package com.calsoft.pos.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Service
public class BarcodeService {
	public byte[] generateBarcode(String code, BarcodeFormat format, int width, int height)
			throws WriterException, IOException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(code, format, width, height);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);
		return baos.toByteArray();
	}
}
