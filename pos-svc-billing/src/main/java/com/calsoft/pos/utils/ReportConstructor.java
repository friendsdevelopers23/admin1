package com.calsoft.pos.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.model.order.SalesFlatOrderAddress;
import com.calsoft.reports.util.ReportTemplate;
import com.calsoft.reports.util.ReportType;
import com.calsoft.reports.vo.ReportVO;

@Component
public class ReportConstructor {

	public ReportVO constructOrderReport(SalesFlatOrder order, Locale locale) {
		List<SalesFlatOrder> beanColl=new ArrayList<SalesFlatOrder>();
		final List<SalesFlatOrderAddress> shippingAddrList = order.getSalesFlatOrderAddress();
		if(!CollectionUtils.isEmpty(shippingAddrList))
		{
			order.setShippingAddress(shippingAddrList.get(0));
		}
		beanColl.add(order);
		ReportVO reportVO = new ReportVO();
		reportVO.setReportType(ReportType.PDF);
		reportVO.setTemplateId(ReportTemplate.ORDER);
		reportVO.setBeanColl(beanColl);
		return reportVO;
	}
	
	public ReportVO constructInvoiceReport(SalesFlatOrder order, Locale locale) {
		List<SalesFlatOrder> beanColl=new ArrayList<SalesFlatOrder>();
		final List<SalesFlatOrderAddress> shippingAddrList = order.getSalesFlatOrderAddress();
		if(!CollectionUtils.isEmpty(shippingAddrList))
		{
			order.setShippingAddress(shippingAddrList.get(0));
		}
		beanColl.add(order);
		ReportVO reportVO = new ReportVO();
		reportVO.setReportType(ReportType.PDF);
		reportVO.setTemplateId(ReportTemplate.INVOICE);
		reportVO.setBeanColl(beanColl);
		return reportVO;
	}

}
