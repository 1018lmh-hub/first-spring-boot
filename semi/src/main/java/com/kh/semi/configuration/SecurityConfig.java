package com.kh.semi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.formLogin().disable().build(); < -- 구시대적 문법
		// 신세대 문법
		
		/*
		return http.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
		
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				t.disable();
				
			}
			
		}).build();
		*/
//		return http.formLogin(t -> t.disable()).build();
		
		//Security에서 403를 반환해줌=> CSRF(Cross site Request Forgery)가 튀어나옴
		//<img src="www.naver.com"/>
		
		//생각 ) 회원가입, 로그인 => 누구나 다 할 수 있어야함
		//		회원정보수정, 탈퇴 = > 로그인 한 사용자만 할 수 있어야함
		//
		
		
		return http.formLogin(AbstractHttpConfigurer::disable)
				   .csrf(AbstractHttpConfigurer::disable)
				   .cors(AbstractHttpConfigurer::disable)
				   .authorizeHttpRequests(requests -> {
					   // POST 방식으로 / members라는 요청이 오면 권한체크  안하고 전부 허용
					   requests.requestMatchers(HttpMethod.POST,"/api/members", "/api/auth/login").permitAll();
				   })
				   .build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
