package com.dajo.proj.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dajo.proj.model.FileType;
import com.dajo.proj.services.JesperReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Controller
@RequestMapping("reports")
public class JasperReportsController {

	@Autowired
	private JesperReportService jasperReportService;
	
	@GetMapping("/test-report")
	public void testReport(ServletRequest request, ServletResponse response) {
		
		try {
			JRPdfExporter exporeterExporter = jasperReportService.exportReportToOutputStreamAsPdf(FileType.PDF, null, "sample_report");
			exporeterExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			exporeterExporter.exportReport();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
