package com.hhf.alaska.spring.boot.starter.zuul.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang hong fei
 */
@RestController
public class ZuulClientController {

	@RequestMapping("/testing/{id}")
	public ResponseEntity callBalanced(@PathVariable long id){
		return ResponseEntity.ok(id);
	}
}
