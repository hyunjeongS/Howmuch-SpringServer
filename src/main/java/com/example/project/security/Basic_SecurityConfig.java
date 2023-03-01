package com.example.project.security;
//package com.example.project.security;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
////	@Autowired
////	private DataSource dataSource;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests()
//		.anyRequest().authenticated()
//		.and()
//		.httpBasic();
//	}
//
////	  @Override
////	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////	    auth.jdbcAuthentication()
////	    .dataSource(dataSource)
////	    .rolePrefix("ROLE_")
////	    .usersByUsernameQuery("select id, pw, true from serveruser where id = admin")
////	    .authoritiesByUsernameQuery("select id, role from serveruser where id = admin");
////	  }
//	  
//	
//	
//	// 메모리 인증
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password(((BCryptPasswordEncoder) passwordEncoder()).encode("admin")).roles("USER");
//	}
//
//	// 비번 암호화
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}