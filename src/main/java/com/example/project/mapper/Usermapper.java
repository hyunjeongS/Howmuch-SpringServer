package com.example.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.project.dao.User;
import com.example.project.dto.UserEdit;

@Mapper
public interface Usermapper {
	
	@Select("SELECT * FROM user WHERE userid=#{id}")
    User searchUser(@Param("id") String id);
	
//	// 회원가입
//	@Insert("INSERT INTO user VALUES(#{user.id}, #{user.pw}, #{user.name}, #{user.phonenumber})")
//	int insertUser(@Param("user") User user);

//	// 로그인
//	@Select("SELECT * FROM user WHERE id=#{user.id} and pw=#{user.pw}")
//	String loginUser(@Param("user") User user);

	// ID중복검사
	@Select("SELECT * FROM user WHERE userid=#{user.userid}")
	String checkId(@Param("user") User user);
	
	// 회원정보 수정
	@Update("UPDATE user set username=#{user.username}, phonenumber=#{user.phonenumber} WHERE userid=#{user.userid}")
	boolean editUser(@Param("user") UserEdit userEdit);
	
	// 비밀번호 찾기
	@Update("UPDATE user set pw=#{user.pw} WHERE userid=#{user.userid}")
	boolean FindPw(@Param("user") User user);
	
	// 아이디 찾기
	@Select("SELECT userid FROM user WHERE username=#{user.username} and phonenumber=#{user.phonenumber}")
	String FindId(@Param("user") User user);
	
//	//이름 변경
//	@Update("UPDATE user set name=#{user.name} WHERE id=#{user.id}")
//	boolean editName(@Param("user") User user);
//	 #{user.address}, #{user.email},
//	//주소 변경
//	@Update("UPDATE user set address=#{user.address} WHERE id=#{user.id}")
//	boolean editAddress(@Param("user") User user);
//	
//	//이메일 변경
//	@Update("UPDATE user set email=#{user.email} WHERE id=#{user.id}")
//	boolean editEmail(@Param("user") User user);
//	
//	//폰번호 변경
//	@Update("UPDATE user set phonenumber=#{user.phonenumber} WHERE id=#{user.id}")
//	boolean editPhonenumber(@Param("user") User user);
}
