package com.example.project.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.dao.User;
import com.example.project.mapper.Usermapper;
import com.example.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {
	private final Usermapper mapper;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean join(User user) {
		if (mapper.checkId(user) == null) {
			user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
			user.setRoles("ROLE_USER");
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
}
