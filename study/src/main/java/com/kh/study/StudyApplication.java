package com.kh.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

	//어플리케이션을 구동 -> @SpringBootApplication에노테이션이 붙은 클래스의 main메소드를 호출
	
	//@EnableAutoConfiguration => ComponentScan으로 컴포넌트 쫙 스캔 => 모든 자동구성이 활성화가 끝나면 애플리케이션을 실행
	//AutoConfoiguration(자동 구성)
	// 스프링부트의 핵심 기능 => 어플리케이션의 클래스패스에존재하는 라이브러리 및 설정을 기반으로 Bean을 싸그리몽당 자동 구성해줌
	//개발자가 설정 하나도 안하고 바로 개발을 시작할 수 있음
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
