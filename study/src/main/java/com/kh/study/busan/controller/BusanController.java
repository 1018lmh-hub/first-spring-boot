package com.kh.study.busan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.busan.model.dto.ReviewDto;
import com.kh.study.busan.model.dto.UpdateReviewDto;
import com.kh.study.busan.model.service.BusanService;

@RestController
@RequestMapping("/api/busans")
@CrossOrigin("*")
public class BusanController {
	
	@Autowired
	private BusanService service;
	
	@GetMapping
	public String getRes(@RequestParam(name="page") int page) {
		return service.getRes(page);
	}
	
	@GetMapping("/{seq}")
	public String getDetail(@PathVariable(name="seq") int seq) {
		return service.getDetail(seq);
	}
	
	@PostMapping("/{seq}/reviews")
	public ResponseEntity<?> save(@PathVariable(value="seq") Long ucSeq, @RequestBody ReviewDto review){
		service.save(ucSeq, review);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{seq}/reviews")
	public ResponseEntity<List<ReviewDto>> findBySeq(@PathVariable(value="seq") Long ucSeq){
		List<ReviewDto> reviews = service.findBySeq(ucSeq);
		return ResponseEntity.ok(reviews);
		
	}
	
	@PatchMapping("/{seq}/reviews")
	public ResponseEntity<Void> update(@PathVariable(value="seq") Long ucSeq,
									   @RequestBody UpdateReviewDto urd){
		service.update(ucSeq, urd);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{seq}/reviews")
	public ResponseEntity<Void> delete(@PathVariable(value="seq") Long ucSeq,
									   @RequestBody UpdateReviewDto urd){
		service.delete(ucSeq, urd);
		return ResponseEntity.noContent().build();
	}
	
	
	/*
	 * 
	 * 1.REST API 구현(C,R,U,D)
	 * 2. 유효성 검사
	 * 3. REST 방식의 예외처리
	 * 4. API 명세 작성
	 * 5. API 테스트
	 */
	/*
	 * 사용자 검증 코드에 대한 중복
	 * 사람의 실수 => 코드를 누락
	 * 
	 * 인증(Authentication) : 너 누구야 who are you -> 로그인으로 해결
	 * 인가(Authorization) : 너 이거 할 권한 있어? -> 본인글만 삭제, 관리자만 삭제
	 * 
	 * Filter - Servlet - intercepter - Handler
	 * 순으로 처리 필터가 좋다~
	 */
	
	
		

}
