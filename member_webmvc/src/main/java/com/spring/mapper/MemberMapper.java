package com.spring.mapper;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public String getPass(String userid);
	public AuthDTO login(String userid);
	public int register(MemberDTO dto);
}
