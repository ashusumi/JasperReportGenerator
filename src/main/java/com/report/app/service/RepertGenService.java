package com.report.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.report.app.model.Item;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

@Service
public class RepertGenService {
	
	
	
	public byte[] getItemReport(List<Item> items,String format) {
		JasperReport jasperReport;
		
		try {
			File file=ResourceUtils.getFile("classpath:item_report.jrxml");
			jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
			JRSaver.saveObject(jasperReport, "item-report.jasper");
			JRBeanCollectionDataSource col=new JRBeanCollectionDataSource(items);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("title", "Item report");
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,col);
			byte[] bs=JasperExportManager.exportReportToPdf(jasperPrint);
			return bs;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
