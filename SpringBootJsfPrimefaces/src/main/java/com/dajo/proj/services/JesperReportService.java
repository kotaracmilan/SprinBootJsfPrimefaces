package com.dajo.proj.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.dajo.proj.model.FileType;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Slf4j
@Service
public class JesperReportService {

	private Map<String, Object> parameters;


	public JasperReport loadCompiledReport(String name) throws JRException {

		return (JasperReport)JRLoader.loadObjectFromFile(name);
	}


	public JRAbstractExporter exportReport(FileType fileType, List<Object> data, String reportName) throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile("classpath:jasper_report/"+reportName+".jrxml");
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jp = JasperFillManager.fillReport(report, parameters, dataSource);
		
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(
		  new SimpleOutputStreamExporterOutput("report_"+reportName+".pdf"));

		SimplePdfReportConfiguration reportConfig
		  = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig
		  = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("Dajo_System");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();
		return exporter;
		//JasperExportManager.exportReportToPdf(jp);
	}
	
	public JRPdfExporter exportReportToOutputStreamAsPdf(FileType fileType, List<Object> data, String reportName) throws FileNotFoundException, JRException {
		return (JRPdfExporter) exportReport(fileType, data, reportName);
		
		
	}





}
