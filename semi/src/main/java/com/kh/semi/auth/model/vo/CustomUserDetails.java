package com.kh.semi.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails{
	private String username; // MEMBER_ID를 담겠음
	private String password;
	private String memberName;
	private Collection<? extends GrantedAuthority> authorities;
	private String status;
}
