package com.calsoft.pos.utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.calsoft.pos.model.CoreConfigData;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.repository.ConfigRepository;
import com.calsoft.notification.vo.NotificationVO;

@Component
public class MailConstructor {

	

	@Autowired
	ConfigRepository configRepository;

	@Value("${image.url.path}")
	String primaryUrl;

	@Value("${image.url}")
	String imageUrl;

	public NotificationVO constructOrderConfirmationEmail(SalesFlatOrder order, Locale locale, String tenantId) {
		NotificationVO notificationVO = new NotificationVO();
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
		String createdDate = formatter.format(order.getCreatedDate().getTime());
		CoreConfigData data = configRepository.getAllDetails(tenantId);
		String uploadFolder = primaryUrl + "/" + tenantId + "?fileName=" + imageUrl;
		String logo = data.getBaseUrl() + uploadFolder;
		params.put("order", order);
		params.put("clientAddress", data);
		params.put("cartItemList", order.getSalesFlatOrderItem());
		params.put("orderPlacedDate", createdDate);
		params.put("imageUrl", logo);
		notificationVO.setMailParams(params);
		notificationVO.setTemplateId(data.getOrderTemplateId());
		notificationVO.setFromAddress(data.getSmtpUsername());
		notificationVO.setToAddress(order.getCustomerEmail());
		notificationVO.setHost(data.getSmtpHost());
		notificationVO.setPort(data.getSmtpPort());
		notificationVO.setUsername(data.getSmtpUsername());
		notificationVO.setPassword(data.getSmtpPassword());
		notificationVO.setDisableEmaill(data.getDisableEmailCommunication()==null?0:Integer.valueOf(data.getDisableEmailCommunication()));
		notificationVO.setSubject("Order Confirmation - " + order.getEntityId());
		return notificationVO;
	}

}
