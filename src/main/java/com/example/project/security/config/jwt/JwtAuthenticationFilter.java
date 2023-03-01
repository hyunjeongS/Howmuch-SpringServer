package com.example.project.security.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.project.dao.User;
import com.example.project.dto.JwtDto;
import com.example.project.security.config.auth.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// ������ ��ť��Ƽ���� UsernamePasswordAuthenticationFilter�� ����
// /login ��û�ؼ� username, password �����ϸ� (post)
// UsernamePasswordAuthenticationFilter ������

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// /login��û�� �ϸ� �α��� �õ��� ���ؼ� ����Ǵ� �Լ�
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter : �α��� �õ���");

		// 1. username, password �޾Ƽ�
		// 2. �������� �α��� �õ��� �غ���. authenticationManager�� �α��� �õ� �ϸ�
		// PrincipalDetailsService�� ȣ�� loadUserByUsername() �Լ� ����
		// 3.PrincipalDetails�� ���ǿ� ��� (���� ������ ���ؼ�)
		// 4. JWT ��ū�� ���� �������ָ� ��

		try {
			// json parsing
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					user.getUserid(), user.getPw());

			// PrincipalDetailsService�� loadUserByUsername() �Լ��� ����� �� �����̸� authentication��
			// ���ϵ�
			// DB�� �ִ� username�� password�� ��ġ�Ѵ�.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("�α��� �Ϸ��: " + principalDetails.getUser().getUserid()); // ��µǸ� �α��� ���������� �Ϸ�

			// authentication ��ü�� session������ �����ϰ� return
			// return ������ ���� ������ security�� ��� ���ֱ� ������ ����
			// JWT ��ū�� ������ ���� ������ ������ ���� ���� ó�������� session�� �־� �ش�.
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {
			try {
				ReturnJson("false", response, "false");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

	// attemptAuthentication���� �� ������ ���������� �Ǿ����� successfulAuthentication �Լ��� ����
	// �ش� �Լ����� JWT ��ū�� ���� request��û�� ����ڿ��� JWT��ū�� response���ָ� ��
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

		// Hash��ȣ���
		String jwtToken = JWT.create().withSubject(principalDetails.getUser().getUserid())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.withClaim("id", principalDetails.getUser().getId())
				.withClaim("userid", principalDetails.getUser().getUserid())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));

		// json���� response
		ReturnJson(jwtToken, response, "true");

//		// header�� JwtToken �Ǿ response
//		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
	}

	private void ReturnJson(String jwtToken, HttpServletResponse response, String status) throws IOException {
		PrintWriter out = response.getWriter();
		JwtDto jwt = new JwtDto();
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		jwt.setAuthorization(JwtProperties.TOKEN_PREFIX + jwtToken);
		jwt.setStatus(status);
		String jsonString = objectMapper.writeValueAsString(jwt);
		out.print(jsonString);
		out.flush();
	}
}