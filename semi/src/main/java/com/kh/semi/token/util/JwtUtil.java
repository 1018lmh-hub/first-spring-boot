package com.kh.semi.token.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.semi.auth.model.vo.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

	//토큰을 만드는 기능, 토큰을 검증하는 기능
	
	// header, payload, signature
	//D9uLKVc4FE0IEheN3pLLEb8GKhHRsG8cdOxazHnEkRm3CGAeB+xVbt8Dbob1QhRB
	
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	
//	Jwts.builder().subject(user.getUsername()).issuedAt(new Date())
//	  .expiration(new Date()).compact();
	
	@PostConstruct
	public void init() {
//		log.info("{}", secretKey);
		byte[] arr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(arr);
	}

	public String getAccessToken(CustomUserDetails user) {
		
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
//				   .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
//				   .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMinutes(15)))
				   .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(5))))
				   .claim("memberName", user.getMemberName())// 보통 accessToken이랑 RefreshToken은 여기서 타입으로 구분  
				   .signWith(key)
				   .compact();
	}
	/*
	 * AccessToken: 일반적으로 짧은 만료기간을 가지도록 생성함
	 * 
	 * +
	 * 
	 * RefreshToken은 일반적으로 AccessToken에 비해 긴 만료기간으로 설정해서 생성함
	 * 
	 * 세미 프로젝트 조
	 * 

	 * 
	 * 5444
	 * 
	 * 4조
	 * 
	 * 17명
	 * 
	 * 오늘의 할 일
	 * 
	 * 첫번째 : 자기소개
	 * 팀 이름 정하기
	 * 우리는 아침에 8시에 모여서 회의를 하자
	 * 우리는 점심시간에 밥을 30까지 먹고~
	 * 등등
	 * 
	 * 그라운드 룰
	 * 
	 * 시간 및 날짜 주기
	 * 어떻게 만날 건지 등등
	 * 
	 * 소통 방식 정하기
	 * 회의 형태를 정하기
	 * 회의록 작성
	 * 
	 * 처음엔 모든 얘기(처음에 클로바 노트 쓰면 ㄱㅊ을듯)
	 * 나중에 수정방안 제안
	 * 
	 * 주제에 대한 고민 => 결정 X
	 * 공유 전기차 / 탄소중립
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public String getRefreshToken(CustomUserDetails user) {
		
		return Jwts.builder()
				   .subject(user.getUsername())
				   .issuedAt(new Date())
				   .expiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
				   .claim("memberName", user.getMemberName())
				   .signWith(key)
				   .compact();
	}
	
	public Claims parseJwt(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
}
