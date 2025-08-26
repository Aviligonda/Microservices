package com.bridgelabz.service;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.util.TokenUtil;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Properties;

@Component
@Slf4j
public class MailService {
	@Autowired
	TokenUtil tokenUtil;

	public static void send(String toEmail, String body, String subject) {
		final String fromEmail = System.getenv("Email");
		final String password = System.getenv("Password");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(properties, authenticator);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/HTML;charset =UTF-8");
			message.setHeader("format", "flowed");
			message.setHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress("no_replay@gmail.com", "NoReply"));
			message.setReplyTo(InternetAddress.parse(System.getenv("Email"), false));
			message.setSubject(subject, "UTF-8");
			message.setText(body, "UTF-8");
			message.setSentDate(new Date());
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(message);
			log.info("Email Sent Successfully.........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLink(String link, Long id) {
		return link + tokenUtil.createToken(id);
	}
}
