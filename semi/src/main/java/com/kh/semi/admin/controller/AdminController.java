package com.kh.semi.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public String findAllMembers() {
		//DB에서 전체 조회한 정보
		return "회원 전체 정보";
	}
	
	//작성
	
	//조회
	
	// 수정           
}

/*
 * 미션 변경
 * 
 * 관리자 기능 구현하기
 * 
 * 관리자가 익명 게시판을 관리할 수 있도록
 * 
 * RUD 추가
 * 그냥 삭제 수정 맘대로 그냥 비밀번호 무시
 * R 삭제한 것도 볼 수 있음
 * 
 * 
 */
