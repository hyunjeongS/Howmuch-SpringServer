package com.example.project.service;

import org.springframework.stereotype.Service;

import com.example.project.dto.UserEdit;
import com.example.project.mapper.Usermapper;

@Service
public class EditUserService {
	private Usermapper mapper;

	public EditUserService(Usermapper mapper) {
		this.mapper = mapper;
	}

	// 회원정보 수정
	public boolean edit(UserEdit userEdit) {
		if (userEdit.getUsername() != null && userEdit.getPhonenumber() != null) {
			mapper.editUser(userEdit);
			return true;
		} else {
			return false;
		}
	}
}
