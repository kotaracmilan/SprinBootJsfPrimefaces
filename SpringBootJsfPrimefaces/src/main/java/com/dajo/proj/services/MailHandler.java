package com.dajo.proj.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailHandler {

	@Autowired
	private JavaMailSender jms;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfigurer;

	
	@Value("classpath:/images/header.jpeg")
	private Resource headerFile;
	
	@Value("classpath:/images/logo.png")
	private Resource logoFile;
		
	
	/**
	 * SENDS HTML EMAIL
	 * You must define logo if you want to use it inline, or you mey comment it out.
	 * Akso change setFrom parameter to be adjusted to your demand
	 * 
	 * @param email - email address where the message has to be sent
	 * @param subject - subject of the email
	 * @param body - html formated body of the mail
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendHtmlEmail(String email, String subject, String body ) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mm = jms.createMimeMessage();

		MimeMessageHelper mmh = new MimeMessageHelper(mm, true,"UTF-8");
		mmh.addTo(email);
		mmh.setFrom("test@localhost");
		//mmh.addBcc();
		mmh.setText(body, true);
		mmh.setSubject(subject);
		mmh.addInline("logo.jpeg", logoFile);
		mmh.addInline("logo-inv.png", headerFile);
		
		jms.send(mm);
	}
	
	
	
	/**
	 * Send email based on FreeMaker template
	 * @param <T> - class taype of object containing data
	 * @param nameOfFreeMarkerTemplateFile - file maker email template that will be used 
	 * @param model - object of class <T> containing data
	 * @param destinationEmail - email address where to send email
	 * @param subject - email subject
	 */
	public <T> void sendMailFromTemplate(String nameOfFreeMarkerTemplateFile, Class<T> model, String destinationEmail, String subject) {
		Template freemarkerTemplate;
		try {
			freemarkerTemplate = freemarkerConfigurer.createConfiguration()
				      .getTemplate(nameOfFreeMarkerTemplateFile);
			String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, (T)model);
			sendHtmlEmail(destinationEmail, subject, htmlBody );
		} catch (Exception e) {
			log.error(e.getMessage());

		}		
			 
	}
	
	
}
