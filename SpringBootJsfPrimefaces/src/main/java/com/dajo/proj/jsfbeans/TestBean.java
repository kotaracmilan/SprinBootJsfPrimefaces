package com.dajo.proj.jsfbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import com.dajo.proj.model.Product;
import com.dajo.proj.services.TestService;

@Named("testBean")
@ApplicationScope
public class TestBean {

	private String time;
	private List<Product> products;
    @Autowired
    private TestService service;
	
	@PostConstruct
	public void init() {
		time = String.valueOf(System.currentTimeMillis());
		 products = service.getProducts(10);
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
