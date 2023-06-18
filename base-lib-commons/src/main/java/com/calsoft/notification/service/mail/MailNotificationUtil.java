package com.calsoft.notification.service.mail;

import com.calsoft.notification.service.NotificationService;
import com.calsoft.notification.vo.NotificationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

@Service
public class MailNotificationUtil implements NotificationService {

	Logger logger = LoggerFactory.getLogger(MailNotificationUtil.class);
	@Autowired
	private Environment env;

	@Autowired
	private TemplateEngine templateEngine;

	Session mailSession;

	@Override
	public boolean invoke(NotificationVO notificationVO) {
		logger.info("Invoking Email Notification :: {}", notificationVO.toString());
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			Properties prop = new Properties();
			if (notificationVO.getHost() != null) {
				if (!notificationVO.getHost().trim().isEmpty()) {
					prop.put("mail.smtp.host", notificationVO.getHost());
					prop.put("mail.smtp.auth", "true");
					prop.put("mail.smtp.starttls.enable", "true"); // TLS
					prop.put("mail.smtp.starttls.required", "true"); // TLS
					prop.put("mail.smtp.port", notificationVO.getPort()); // TLS
					prop.put("multipart.max-file-size", "10mb");
					prop.put("multipart.max-request-size", "10mb");
					mailSender.setJavaMailProperties(prop);
					final String username = notificationVO.getUsername();
					final String password = notificationVO.getPassword();
					Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
					mailSender.setSession(session);
				}
			}

			mailSender.send(buildMailAttributes(notificationVO, notificationVO.getUsername()));
		} catch (Exception exp) {
			logger.info("Expcetion while trying to invoke mail notification ", exp);
			return false;
		}
		return true;
	}

	public MimeMessagePreparator buildMailAttributes(NotificationVO notificationVO, String username)
			throws AddressException, MessagingException {

		String text = mailContent(notificationVO.getMailParams(), notificationVO.getTemplateId());

		try {

			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper emailList = new MimeMessageHelper(mimeMessage);
					emailList.setTo(notificationVO.getToAddress());
					emailList.setSubject(notificationVO.getSubject());
					emailList.setText(text, true);
					emailList.setFrom(new InternetAddress(username));
					emailList.setTo(notificationVO.getToAddress());
					if (notificationVO.getToEmails() != null) {

						List<String> arrayList = new ArrayList<>();
						arrayList.add(notificationVO.getToAddress());

						Collections.addAll(arrayList, notificationVO.getToEmails());

						String[] stringArray = arrayList.toArray(new String[0]);

						emailList.setTo(stringArray);
					}
				}

			};
			return messagePreparator;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public String mailContent(Map<String, Object> mailParams, String templateId) {
		Context context = new Context();
		mailParams.forEach((key, value) -> context.setVariable(key, value));
		return templateEngine.process(templateId, context);
	}
}
