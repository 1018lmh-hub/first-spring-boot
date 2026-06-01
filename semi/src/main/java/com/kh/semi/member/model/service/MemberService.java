package com.kh.semi.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.semi.exception.DuplicateMemberIdException;
import com.kh.semi.exception.FailSignUpException;
import com.kh.semi.member.model.dao.MemberMapper;
import com.kh.semi.member.model.dto.MemberDto;
import com.kh.semi.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	/*
	 * SQL문 두 번 수행
	 * 
	 * 여러 SQL문의 트랜잭션을 한번에 처리
	 * 
	 * 확장성 => 미래의 메소드에 한 줄 추가 됐을 때 원자성이 자동으로 보장됨
	 * 
	 * 컨벤션, 유지보수
	 * 
	 * 커넥션, sqlSession 재활용
	 * 
	 * 현재 메소드는 @Transactional를 붙이나 안붙이나 기능상의 차이는 존재하지 않음
	 */
	@Transactional
	public void signUp(MemberDto member) {
		
		// 비밀번호 암호화
		
		// 아이디 중복검사
		int count = memberMapper.countByMemberId(member.getMemberId());
		
		if(count > 0) {
			throw new DuplicateMemberIdException("이미 존재하는 아이디입니다.");
		}
		
		Member memberEntity = Member.builder().memberId(member.getMemberId())
								    .memberPwd(passwordEncoder.encode(member.getMemberPwd()))
								    .memberName(member.getMemberName())
								    .role("ROLE_USER")
								    .build();
		
//		log.info("만들어진 맴버 : {}" , memberEntity);
		
		
		int result = memberMapper.signUp(memberEntity);
		// 일반적 => Mapper에서 성공 실패 여부를 반환 => 정수값으로~
		
		if(1> result ) {
			throw new FailSignUpException("잠시 후 다시 시도해주세요.");
		}
		
	}
	
	//실습 겸 숙제
	// 오늘 했던 작업을 그대로 반복
	// 새프로젝트 만들기 => 새 회원용 테이블 만들기
	// 새 회원가입 기능 만들기 => 1절부터 4절까지 차근차근
}
