package com.example.project.service;

import org.springframework.stereotype.Service;

import com.example.project.dao.User;
import com.example.project.mapper.Usermapper;

@Service
public class FindService {
	private Usermapper mapper;
	
	public FindService(Usermapper mapper) {
		this.mapper = mapper;
	}
	
	public String FindUserId(User user) {
		String userid = mapper.FindId(user);
		if(userid!=null) {
			return userid;
		}
		else {
			return "false";
		}
	}
	
	public void FindUserPw(User user) {
		mapper.FindPw(user);	
	}
	
}
