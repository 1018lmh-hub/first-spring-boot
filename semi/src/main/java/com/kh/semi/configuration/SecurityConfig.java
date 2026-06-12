package com.kh.semi.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kh.semi.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	//토큰이랑 AuthenticationManager 로그인에 필요한거라 회우ㅠㅓㄴ가입할 때 빼뻐리기
	private final JwtFilter jwtFilter;

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
				   .cors(Customizer.withDefaults())
				   .authorizeHttpRequests(requests -> {
					   // POST 방식으로 / members라는 요청이 오면 권한체크  안하고 전부 허용
					   requests.requestMatchers(HttpMethod.POST,"/api/members", "/api/auth/login", "/api/auth/refresh").permitAll();
					   // PATCH방식으로 /api/members라는 요청이 오면 이녀석 인증이 된건가?
					   requests.requestMatchers(HttpMethod.PATCH, "/api/members", "/api/boards/**").authenticated();
					   requests.requestMatchers(HttpMethod.DELETE, "/api/members", "/api/boards/**").authenticated();
					   requests.requestMatchers(HttpMethod.POST,"/api/boards", "/api/comments").authenticated();
					   requests.requestMatchers(HttpMethod.GET, "/api/boards/**", "/api/comments", "/uploads/**", "/api/auth/logout").permitAll();
					   requests.requestMatchers("/api/admin").hasRole("ADMIN");// 시큐리티가 자동으로 앞에 ROLE_을 붙여줌
					   //requests.requestMatchers("/api/admin").hasAuthority("ROLE_ADMIN");//ROLE_ADMIN과 완전히 일치하는 지
					   //requests.requestMatchers("/api/admin").hasAnyRole("ADMIN", "USER");// 하나라도 있는면 얘도 ROLE_을 붙여줌
				   }).sessionManagement(manager -> 
				   						manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
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
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174"));
		configuration.setAllowedMethods(Arrays.asList("POST", "PATCH", "DELETE", "GET", "PUT", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
}
