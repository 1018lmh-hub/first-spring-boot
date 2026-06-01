package com.kh.kor.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.kor.user.model.dto.UserDto;
import com.kh.kor.user.model.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<Void> signUp(@RequestBody @Valid UserDto user){
		
//		log.info("넘어오는 거 확인 {}", user);
		
		userService.signUp(user);
		return ResponseEntity.status(201).build();
	}

}
