package com.example.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dao.User;
import com.example.project.service.FindService;

@RestController
@RequestMapping("/find")
public class FindController {
	private FindService findService;

	public FindController(FindService findService) {
		this.findService = findService;
	}

	@PostMapping("/id")
	public String FindUserId(@RequestBody User user) {
		return findService.FindUserId(user);
	}

	@PostMapping("/pw")
	public void FindUserPw(@RequestBody User user) {
		findService.FindUserId(user);
	}
}


