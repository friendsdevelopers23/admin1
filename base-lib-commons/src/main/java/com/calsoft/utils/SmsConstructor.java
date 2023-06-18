package com.calsoft.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calsoft.notification.vo.NotificationVO;
import com.calsoft.pos.model.CoreConfigData;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.repository.ConfigRepository;

@Component
public class SmsConstructor {
	
	@Autowired
	ConfigRepository configRepository;
	
	@Autowired
	public SmsConstructor smsConstructor;
	
	@Autowired
	private SmsUtils smsUtils;

	public NotificationVO notificationSetSms(CoreConfigData coreConfigData, String messagId,String param,String number) {

		NotificationVO notificationVO = new NotificationVO();

		notificationVO.setSmsUrl(coreConfigData.getSmsUrl());
		notificationVO.setSmscApiKey(coreConfigData.getSmscApiKey());
		notificationVO.setSmsUrl(coreConfigData.getSmsUrl());
		notificationVO.setMessage(messagId);
		notificationVO.setSmscClientId(coreConfigData.getSmscClientId());
		notificationVO.setSmscSenderId(coreConfigData.getSmscSenderId());
		notificationVO.setNumber(number);
		notificationVO.setSmsValue(param);
		notificationVO.setSmsVendor(coreConfigData.getSmsVendor());
		notificationVO.setSmscApiKey(coreConfigData.getSmscApiKey());
		notificationVO.setSmsValue(param);

		return notificationVO;
	}

	public void sendSms(SalesFlatOrder salesFlatOrder, String tenantId,String message,String key) {
	
		CoreConfigData coreConfigData = configRepository.getAllDetails(tenantId);
		if(message.equalsIgnoreCase("order")) {
			message= coreConfigData.getOrderMessageTemplate();
		}else if (message.equalsIgnoreCase("invoice")) {
			message= coreConfigData.getInvoiceMessageTemplate();
		}else if (message.equalsIgnoreCase("shipment")) {
			message= coreConfigData.getShipmentMessageTemplate();
		}else if (message.equalsIgnoreCase("credit")) {
			message= coreConfigData.getRefundMessageTemplate();
		}
			
		String extOrderId = salesFlatOrder.getExtOrderId();
		String number = salesFlatOrder.getSalesFlatOrderAddress().get(0).getTelephone();
		
		
		NotificationVO notificationVO = smsConstructor.notificationSetSms(coreConfigData, message, extOrderId, number);
		if (coreConfigData.getSmsDisabled() == 0) {

			smsUtils.triggerSms(notificationVO, key, tenantId);

		}

	}

}
