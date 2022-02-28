package com.dajo.proj.controllers;

import java.util.List;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dajo.proj.model.Product;
import com.dajo.proj.services.TestService;

@RestController
@RequestMapping("rest")
public class JsonEdpoint {

	@Autowired
	private TestService testService;
	
	
	@GetMapping("/sample-data")
	public List<Product> getProducts() {
			
			return testService.getProducts();
		 
		
	}
}
