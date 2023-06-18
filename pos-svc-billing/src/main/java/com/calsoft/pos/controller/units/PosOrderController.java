package com.calsoft.pos.controller.units;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.exception.InvalidException;
import com.calsoft.pos.service.PosOrderService;
import com.calsoft.utils.UserUtils;
import com.lowagie.text.DocumentException;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api")
@RestController
@Slf4j
public class PosOrderController {

	@Autowired
	public PosOrderService orderService;

	@Autowired
	public UserUtils userUtils;

	/***
	 * this method using cart information place order conform into paynow page move
	 * using
	 *
	 * @param salesFlatOrder
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/order")
	public ResponseWrapper saveSalesFlatOrder(@RequestBody final SalesFlatOrder salesFlatOrder,
			HttpServletRequest request, @RequestHeader("x-tenant") String tenantId) throws ParseException {
		String token = userUtils.getToken(request);
		String customerId = userUtils.getUserIdFromToken(token);

		String userName = userUtils.getUserNameFromToken(token);

		salesFlatOrder.setBillerId(Long.valueOf(customerId));
		salesFlatOrder.setBiller(userName);
		salesFlatOrder.setPos(1);
		return orderService.saveOrderItem(salesFlatOrder, request, tenantId,customerId);

	}

	@GetMapping("/order/invoice/{entityId}")
	public StreamingResponseBody downloadPDFResourceAdmin(HttpServletResponse response,
			@RequestHeader("x-tenant") String tenantId, HttpServletRequest request,
			@PathVariable(value = "entityId") String entityId) throws InvalidException {

		StreamingResponseBody outputStream = null;
		try {

			byte[] bytes = orderService.generatePdf(entityId, tenantId);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=order_" + entityId + ".pdf");
			if (bytes != null && bytes.length > 0) {
				log.info("Report generated successfully...");
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				outputStream = getOutputStream(byteArrayInputStream);
			} else {
				log.info("Report Generation failed ");
			}

		} catch (DocumentException | IOException ex) {
			ex.printStackTrace();
		}
		return outputStream;
	}

	@GetMapping("/order/invoice/quote/{entityId}")
	public StreamingResponseBody downloadQuote(HttpServletResponse response, @RequestHeader("x-tenant") String tenantId,
			HttpServletRequest request, @PathVariable(value = "entityId") String entityId) throws InvalidException {

		StreamingResponseBody outputStream = null;
		try {
			String token = userUtils.getToken(request);
			String userName = userUtils.getUserNameFromToken(token);
			byte[] bytes = orderService.generatePdfQuote(entityId, tenantId, userName);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=order_" + entityId + ".pdf");
			if (bytes != null && bytes.length > 0) {
				log.info("Report generated successfully...");
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				outputStream = getOutputStream(byteArrayInputStream);
			} else {
				log.info("Report Generation failed ");
			}

		} catch (DocumentException | IOException ex) {
			ex.printStackTrace();
		}
		return outputStream;
	}

	private StreamingResponseBody getOutputStream(InputStream inputStream2) {
		// TODO Auto-generated method stub
		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream2.read(data, 0, data.length)) != -1) {
				outputStream.write(data, 0, nRead);
			}

		};
	}

}
