package com.example.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.project.dao.User;
import com.example.project.dto.Result;
import com.example.project.dto.Spec;
import com.example.project.mapper.Specmapper;
import com.example.project.mapper.Usermapper;
import com.example.project.security.config.jwt.JwtProperties;

@Service
public class ResultService {
	private Specmapper specMapper;
	private Usermapper userMapper;

	public ResultService(Specmapper specMapper, Usermapper userMapper) {
		this.specMapper = specMapper;
		this.userMapper = userMapper;
	}
	
	//Dto
	public Result values(Result result, Spec spec, HttpServletRequest request) {
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		String userid = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("userid")
				.asString();
		spec.setWidth(result.getWidth());
		spec.setName(result.getName());
		String cost = specMapper.getCost(spec);
		result.setCost(cost);
		User userResult = userMapper.searchUser(userid);
		result.setPhonenumber(userResult.getPhonenumber());
		result.setUsername(userResult.getUsername());
		return result;
	}
	
	
	
	
	//Map
//	public Map<String, Object> values(Map<String, String> param, Spec spec, HttpServletRequest request) {
//		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
//		String userid = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("userid")
//				.asString();
//		Map<String, Object> map = new HashMap<>();
//		spec.setName(param.get("name"));
//		spec.setWidth(Double.parseDouble(param.get("width")));
//		Integer cost = SpecMapper.getCost(spec);
//		User result = UserMapper.searchUser(userid);
//		map.put("name", spec.getName());
//		map.put("cost", cost);
//		map.put("Username", result.getUsername());
//		map.put("phonenumber", result.getPhonenumber());
//		return map;
//	}
}
