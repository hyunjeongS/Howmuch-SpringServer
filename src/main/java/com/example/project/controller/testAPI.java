//package com.example.project.controller;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.project.dao.User;
//import com.example.project.mapper.Usermapper;
//
//@RestController
//public class testAPI {
//	private Usermapper mapper;
//
//	public testAPI(Usermapper mapper) {
//		this.mapper = mapper;
//	}
//
//	@PostMapping("/test")
//	public Map<String, Object> FindUserId(@RequestBody User user) {
//		String userid = mapper.FindId(user);
//		Map<String, Object> map = new HashMap<>();
//		if (userid != null) {
//			map.put("userid", userid);
//			return map;
//		}
//		else {
//			map.put("userid", "NULL");
//			return map;
//		}
//	}
//}
