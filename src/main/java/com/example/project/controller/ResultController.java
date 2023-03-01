package com.example.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.Result;
import com.example.project.dto.Spec;
import com.example.project.service.ResultService;

@RestController
public class ResultController {
	private ResultService resultService;

	public ResultController(ResultService resultService) {
		this.resultService = resultService;
	}

	//Dto
	@PostMapping("/cost")
	public Result values(@RequestBody Result result, Spec spec, HttpServletRequest request) {
		return resultService.values(result, spec, request);
	}
	
	
	
	// Map
//	@PostMapping("/cost")
//	 public Map<String, Object> values(@RequestBody Map<String, String> param, Spec spec, HttpServletRequest request) {
//		return resultService.values(param, spec, request);
//	}
}