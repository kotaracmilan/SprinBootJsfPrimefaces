package com.dajo.proj.services;

import java.io.ByteArrayOutputStream;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

@Slf4j
@Service
public class JesperReportService {

	private Map<String, Object> parameters;


	public JasperReport loadCompiledReport(String name) throws JRException {

		return (JasperReport)JRLoader.loadObjectFromFile(name);
	}


	public byte[] exportReport(FileType fileType, List<Object> data, String reportName) throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile("classpath:jasper_report/"+reportName+".jrxml");
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jp = JasperFillManager.fillReport(report, parameters, dataSource);
		

		 final Exporter exporter;
		 final ByteArrayOutputStream out = new ByteArrayOutputStream();
		    

		    switch (fileType) {
		        case HTML:
		            exporter = new HtmlExporter();
		            exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
		            break;

		        case CSV:
		            exporter = new JRCsvExporter();
		            exporter.setExporterOutput(new SimpleWriterExporterOutput(out));
		            break;

		        case XML:
		            exporter = new JRXmlExporter();
		            exporter.setExporterOutput(new SimpleXmlExporterOutput(out));
		            break;

		        case XLSX:
		            exporter = new JRXlsxExporter();
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out)); //test
		            break;

		        case PDF:
		            exporter = new JRPdfExporter();
		            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		            break;

		        default:
		            throw new JRException("Unknown report format: " + fileType.toString());
		    }


		    exporter.setExporterInput(new SimpleExporterInput(jp));
		    exporter.exportReport();
		    
		    return out.toByteArray();
	}

	
	





}
