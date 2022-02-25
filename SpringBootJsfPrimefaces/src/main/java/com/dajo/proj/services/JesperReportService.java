package com.dajo.proj.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.dajo.proj.model.FileType;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Slf4j
public class JesperReportService {

	private Map<String, Object> parameters;



	public JesperReportService(Map<String, Object> properties) {
		super();
		this.parameters = properties;
	}


	public JasperReport loadCompiledReport(String name) throws JRException {

		return (JasperReport)JRLoader.loadObjectFromFile(name);
	}


	public void exportReport(FileType fileType, List<Object> data, String reportName) throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile("classpath:/jasper_report/"+reportName);
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jp = JasperFillManager.fillReport(report, parameters, dataSource);
		JasperExportManager.exportReportToPdf(jp);
	}

	private void exportToPdf(JasperPrint jasperPrint) throws JRException {
		JasperExportManager.exportReportToHtmlFile(null);
	}




}
