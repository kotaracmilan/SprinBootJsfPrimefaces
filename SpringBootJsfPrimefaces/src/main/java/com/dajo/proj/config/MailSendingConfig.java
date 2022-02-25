package com.dajo.proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class MailSendingConfig {

	/**
	 * This method will return JavaMailSender Beanb that will be configured based 
	 * on properties defined in application.properties file.
	 * Please define there, as per your mail server:
	 * spring.mail.host
	 * spring.mail.port
	 * spring.mail.username
	 * spring.mail.password
	 * mail.smtp.auth
	   mail.smtp.starttls.enable
	   mail.debug
	   mail.smtp.connectiontimeout
	   mail.smtp.timeout
	   mail.smtp.writetimeout
	 * @return
	 */
	@Bean
	public JavaMailSender configMailServer() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	   	    
	    return mailSender;
	}
	
	@Bean 
	public FreeMarkerViewResolver freemarkerViewResolver() { 
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver(); 
	    resolver.setSuffix(".ftl"); 
	    return resolver; 
	}
	
	/*
	/**
	 * This method will initiate FreeMakerConfigurer Bean so you may use FreeMaker templates for your mail.
	 * Please refer to FreeMaker documentation, to get how to create template (.ftl file)
	 * https://freemarker.apache.org/
	 * 
	 * @return
	 *
	@Bean 
	public FreeMarkerConfigurer freemarkerConfig() { 
	    FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer(); 
	    freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/mail");
	    return freeMarkerConfigurer; 
	}
	*/
	
	
}
