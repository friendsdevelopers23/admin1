package com.calsoft.utils;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calsoft.notification.vo.NotificationVO;
import com.calsoft.pos.kafka.producer.CalsoftEventProducer;

@Component
public class MailUtils {

	@Autowired
	CalsoftEventProducer calsoftEventProducer;

	/***
	 * this method using Send mail
	 * 
	 * @param notificationVO
	 * @param key
	 * @param tenantId
	 * @return
	 * @throws ParseException
	 */
	public void triggerMail(NotificationVO notificationVO, String key, String tenantId) {
		try {
			if (notificationVO.getDisableEmaill() == 0) {
				calsoftEventProducer.sendEvent("mail-events", key, notificationVO, tenantId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
