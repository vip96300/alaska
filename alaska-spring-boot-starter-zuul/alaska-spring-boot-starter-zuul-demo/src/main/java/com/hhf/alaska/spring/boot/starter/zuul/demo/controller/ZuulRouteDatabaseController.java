package com.hhf.alaska.spring.boot.starter.zuul.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang hong fei
 * @version 1.0.0
 */
@RestController
public class ZuulRouteDatabaseController {

	@Autowired
	private RefreshRouteService refreshRouteService;

	@RequestMapping("/refreshRoute")
	public Object refreshRoute() {
		refreshRouteService.refreshRoute();
		return ResponseEntity.ok();
	}

}