package com.hhf.alaska.spring.boot.starter.zuul.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author huang hong fei
 */
@RestController
public class ZuulClientController {

	@Autowired
	private DataSource dataSource;

	@RequestMapping("/testing/{id}")
	public ResponseEntity callBalanced(@PathVariable long id){
		try {
			System.err.println(dataSource.getConnection().getCatalog());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(id);
	}
}
