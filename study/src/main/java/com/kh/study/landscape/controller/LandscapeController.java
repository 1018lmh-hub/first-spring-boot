package com.kh.study.landscape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.landscape.model.service.LandscapeService;

@RestController
@RequestMapping("/api/landscapes")
@CrossOrigin("*")
public class LandscapeController {
	
	@Autowired
	private LandscapeService service;
	
	@GetMapping
	public String getRes(@RequestParam(name="page") int page) {
		return service.getRes(page);
	}
	
	@GetMapping("/{id}")
	public String getDetail(@PathVariable(name="id") String id ) {
		return service.getDetail(id);
	}

}
