package com.article.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public boolean sendEmail(String to, String subject, String body,File file) {
		boolean status = false;
		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			helper.addAttachment(file.getName(), file);
			
			javaMailSender.send(mimeMessage);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	


}
