package com.kh.study.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.study.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {

	@Select("SELECT * FROM MEMBER ORDER BY ENROLL_DATE")
	List<MemberDto> findAll();

	@Select("SELECT * FROM MEMBER WHERE USER_ID = #{userId}")
	MemberDto findById(String userId);
}
