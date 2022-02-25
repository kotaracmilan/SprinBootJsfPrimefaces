package com.dajo.proj.jsfbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import com.dajo.proj.model.Product;
import com.dajo.proj.services.MailHandler;
import com.dajo.proj.services.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named("testBean")
@ApplicationScope
public class TestBean {

	private String time;
	private List<Product> products;
    
	@Autowired
    private TestService service;
	@Autowired
	private MailHandler mailHandler;
	
	@PostConstruct
	public void init() {
		time = String.valueOf(System.currentTimeMillis());
		 products = service.getProducts(10);
	}
	
	public void sendTestMail() {
		System.out.println("Button pressed");
		try {
			Product product = new Product();
			product.setCode("asd");
			product.setId(34);
			product.setName("TestName");
			mailHandler.sendMailFromTemplate("test", product, "kotaracmilan@hotmail.com", "Test mail");
			//mailHandler.sendHtmlEmail("kotaracmilan@hotmail.com", "THis is mail form app", "<body>Hello</body>");
		}
		catch (Exception e) {
			Log.error(e.getMessage());
			System.out.println("Exception:" + e.getMessage());
		}
	}

    public List<Product> getProducts() {
    	return products;
    }


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
    
    
}
