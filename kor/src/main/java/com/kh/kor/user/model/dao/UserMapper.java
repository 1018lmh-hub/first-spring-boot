package com.kh.kor.user.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.kor.user.model.vo.User;

@Mapper
public interface UserMapper {

	@Insert("INSERT INTO KOR_USER VALUES(SEQ_KOR.NEXTVAL, #{userId}, #{userPwd}, #{userName}, SYSDATE, #{role}, 'Y')")
	int signUp(User userEntity);

	@Select("SELECT COUNT(*) FROM KOR_USER WHERE USER_ID=#{userId}")
	int countByUserId(String userId);

}
