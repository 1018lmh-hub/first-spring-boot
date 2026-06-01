package com.kh.kor.user.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.kor.exception.DuplicateMemberIdException;
import com.kh.kor.exception.FailSignUpException;
import com.kh.kor.user.model.dao.UserMapper;
import com.kh.kor.user.model.dto.UserDto;
import com.kh.kor.user.model.vo.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void signUp(UserDto user) {
		
		int count = userMapper.countByUserId(user.getUserId());
		
		if(count > 0 ) {
			throw new DuplicateMemberIdException("이미 존재하는 아이디입니다.");
		}

		User userEntity = User.builder()	  
							  .userId(user.getUserId())
							  .userPwd(passwordEncoder.encode(user.getUserPwd()))
							  .userName(user.getUserName())
							  .role("ROLE_USER")
							  .build();
	
		int result = userMapper.signUp(userEntity);
		
		if(1> result) {
			throw new FailSignUpException("잠시 후 다시 시도해주세요.");
		}
						  
		
	}


}
