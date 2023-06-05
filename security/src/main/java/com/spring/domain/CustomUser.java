package com.spring.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/*
 *  java.util.Collection interface (부모)
 *  - java.util.List interface (자식)
 *  	ㄴ java.util.ArrayList
 *  	ㄴ java.util.LinkedList
 *  
 *  - java.util.Set interface
 *  	ㄴ java.util.HashSet
 *  
 *  - ? extends GrantedAuthority : GrantedAuthority 와 자손들만 가능
 *  
 *  ==> List<GrantedAuthority>, Set<GrantedAuthority>
 *  
 *  ==> dto.getAuthorites() ==> List<SpUserAuthorityDTO>이므로 <GrantedAuthority>과 타입이 안맞음
 *  
 *  ==> 따라서 <SuUserAuthorityDTO>를 <GrantedAuthority> 형태로 객체 생성해주면 됨
 */

@Getter
public class CustomUser extends User {
	
	private SpUserDTO dto;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(SpUserDTO dto) {
		//<SuUserAuthorityDTO>를 <GrantedAuthority> 형태로 타입변환하기 위해 스트림사용하여 객체 생성
		super(dto.getUserid(), dto.getPassword(), dto.getAuthorites().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toList()));
		this.dto = dto;
	}
	
}
