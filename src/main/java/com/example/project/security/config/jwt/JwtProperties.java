package com.example.project.security.config.jwt;

public interface JwtProperties {
	String SECRET = "cos"; // �츮 ������ �˰� �ִ� ��а�
	int EXPIRATION_TIME = (60000*60)*100; // (1/1000��)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}