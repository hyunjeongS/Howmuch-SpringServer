package com.example.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.UserEdit;
import com.example.project.service.EditUserService;

@RestController
@RequestMapping("/edit")
public class EditUserController {


	private EditUserService editUserService;
	
	public EditUserController(EditUserService edituserservice) {
		this.editUserService = edituserservice;
	}
	
	// 회원정보 수정
	@PostMapping("/userprofile")
	public boolean edit(@RequestBody UserEdit userEidt) {
		return editUserService.edit(userEidt);
	}
}
