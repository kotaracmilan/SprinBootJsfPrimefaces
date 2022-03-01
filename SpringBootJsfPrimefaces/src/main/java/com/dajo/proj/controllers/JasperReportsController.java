package com.dajo.proj.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dajo.proj.model.FileType;
import com.dajo.proj.services.JesperReportService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("reports")
public class JasperReportsController {

	@Autowired
	private JesperReportService jasperReportService;
	
	@GetMapping("/test-report")
	public ResponseEntity<?> testReport(ServletRequest request, ServletResponse response) {
		
		try {
			byte[] bArray = jasperReportService.exportReport(FileType.XML, null, "sample_report");
			 HttpHeaders headers = new HttpHeaders();
			 headers.set("Content-type", MediaType.TEXT_HTML_VALUE);
			 headers.set("Content-Disposition","attachment; filename=\"report."+FileType.XML+"\"");
			//response.setContentType("text/html");//.getWriter().wait(bArray);
			//response.sete
			 return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bArray);
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
}
