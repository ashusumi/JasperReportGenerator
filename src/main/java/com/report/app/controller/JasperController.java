package com.report.app.controller;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.report.app.repo.ItemRepo;
import com.report.app.service.RepertGenService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class JasperController {

	@Autowired
	private ItemRepo repo;
	
	@Autowired
	private RepertGenService srvc;
	
	@GetMapping("/report")
	public ResponseEntity<Resource> getReport() {
		byte[] rprt=srvc.getItemReport(repo.findAll(),"pdf");
		ByteArrayResource arrayResource=new ByteArrayResource(rprt);
		return ResponseEntity.ok()
		        .contentType(MediaType.APPLICATION_OCTET_STREAM)
		        .contentLength(arrayResource.contentLength())
		        .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
		            ContentDisposition.attachment()
		                .filename("item-report." +"pdf")
		                .build().toString())
		        .body(arrayResource);
	}
	
	
}
