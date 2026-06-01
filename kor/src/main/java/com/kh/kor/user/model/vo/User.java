package com.kh.kor.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class User {
	
	private Long userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String enrollDate;
	private String role;
	private String status;

}
