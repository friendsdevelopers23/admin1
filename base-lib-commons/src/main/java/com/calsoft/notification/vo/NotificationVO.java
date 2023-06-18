package com.calsoft.notification.vo;

import java.util.Map;

import com.calsoft.notification.util.NotificationType;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotificationVO {

	private String toAddress;
	private String fromAddress;
	private String subject;
	private String text;
	private NotificationType notificationType;
	private Map<String, Object> mailParams;
	private String templateId;
	private String[] toEmails;
	private String username;
	private String password;
	private int port;
	private String host;
	private int disableEmaill;
	private String message;
	private int smsDisabled;
	private String smsVendor;
	private String number;
	private String messageTemplate;
	private String smscApiKey;
	private String smscSenderId;
	private String smscClientId;
	private String smsValue;
	private String smsUrl;
	
	

}