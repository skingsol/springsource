package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.SpUserAuthorityDTO;
import com.spring.domain.SpUserDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	@Transactional   //두개를 하나의 단위로 처리
	public boolean register(SpUserDTO dto) {
		
		//비밀번호 암호화
		dto.setPassword(encoder.encode(dto.getPassword()));		
		
		// 회원가입
		boolean result = mapper.register(dto) == 1;
		
		// 회원권한
		mapper.registerAuth(new SpUserAuthorityDTO(dto.getUserid(), "ROLE_USER"));
		//관리자권환 주는 코드
		//mapper.registerAuth(new SpUserAuthorityDTO(dto.getUserid(), "ROLE_ADMIN"));
		
		return result;
	}
}
