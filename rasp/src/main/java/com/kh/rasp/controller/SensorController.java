package com.kh.rasp.controller;




import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.rasp.model.dto.SensorDto;
import com.kh.rasp.model.entity.Sensor;
import com.kh.rasp.model.service.SensorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
public class SensorController {
	
	private final SensorService sensorService;
	
	
	@PostMapping
	public ResponseEntity<?> receive(@RequestBody SensorDto dto){
		log.info("{}", dto);
		sensorService.save(dto);
		return ResponseEntity.ok("빠이");
	}

	@GetMapping
	public ResponseEntity<List<Sensor>> findAll(){
		return ResponseEntity.ok(sensorService.findAll());
	}
	
	/*
	 *				JPA					MyBatis
	 *
	 *--------------------------------------------------
	 *
	 * SQL	|	 안씀, 자동 생성	|		직접 작성
	 * 관점	|	 객체를 저장 / 조회	|		SQL을 실행
	 * 코드	|	 	 적음			|		많음
	 * 제어력	|	자동이라 세밀 제어	|		SQL문 완전 제어
	 * 		|	튜닝 어려움			|		복작 쿼리 튜닝 유리
	 * 러닝	|	개념(영속성, 매핑)	|		SQL만 알면 직관적
	 * 커브	|	이해 필요			|		
	 * 현업	|	신규, 스타트업		|		SI, 레거시
	 * 		|	기술회사			|	
	 * 
	 * JPA의 save는 INSERT/UPDATE를 알아서 판단함
	 * 인자로 Entity를 전달(@Entity 를 달아놓은 거 VO랑 똑같이 동작, VO대신은 맞는데 똑같이가 맞나)
	 * Entity에 id가 없으면 새 객체로 판단 INSERT문을 수행
	 * Entity에 id가 있으면 기존 객체로 판단 UPDATE문을 수행
	 * 
	 * 자바는 객체 단위(OOP)
	 * 데이터 베이스는 테이블 단위(RDB)
	 * 패러다임 불일치
	 * 
	 * JPA : 자바 객체를 컬렉션에 넣고 빼듯이 DB를 다루자	=> ORM
	 * 
	 * MyBatis : 내가 쓴 SQL을 실행하는 도구			=> SQL Mapper
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 

	 * 
	 * 수학은 따로 배우는 게 맞고, 언어도 좀 익히고,
	 * 다른 분야 용어 공부도 좀 하고, 
	 * 화학 구조는 나한테는 좀 어렵고(그래도 다시 좀 공부하는 게 나으려나 너무 안하긴 했어),
	 * 물리 쪽은 물성이나 재료, 힘 관계 등 좀 다뤄볼만하고,
	 * 생물은 작은 단위 말고, 식물쪽, 균류, 동물(은 좀 애매하고)
	 * 사람 몸 생리학 해부학 정도는 배워볼만 할 거 같은데,
	 * 
	 * 영어가 젤 중요하긴 한디
	 * 학습 속도를 어떻게 해야 좀 올리지,
	 * 안했다가 다시 할라니까 힘드네 
	 * 전에도 학습 속도 자체는 늦는 편이긴 했는데
	 * 선생님들이 잘 가르쳐서 아니 잘 맞아서 좀 잘하게 된듯
	 * 
	 * 
	 * 취미도 좀 하고 간간이 공부할 것도 하고, 꾸준히 좀 뭘 하긴 해야하는디 
	 * 		
	 */
}
