package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

public class MemberMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		MemberService service = (MemberService) ctx.getBean("member");
		
//		//회원가입
//		MemberDTO dto = new MemberDTO();
//		dto.setUserid("kim123");
//		dto.setPassword("kim123");
//		dto.setName("김길동");
//		dto.setGender("남자");
//		dto.setEmail("kim345@naver.com");
//		
//		boolean result = service.insertMember(dto);
//		
//		System.out.println(result ? "회원가입 성공" : "회원가입 실패");		
		
//		//회원수정
//		MemberDTO updateDto = new MemberDTO();
//		updateDto.setUserid("kim123");
//		updateDto.setPassword("kim123");
//		updateDto.setEmail("kim123@naver.com");
//		System.out.println(service.updateMember(updateDto)?"회원수정 성공":"회원수정 실패");
		
		//회원탈퇴
//		System.out.println(service.deleteMember("kim123", "kim123")?"회원탈퇴 성공":"회원탈퇴 실패");
		
		//회원조회
		System.out.println(service.getRow("hong123", "hong123@"));
		
		//회원전체조회
		List<MemberDTO> list = service.getRows();
		for(MemberDTO memberDTO : list) {
			System.out.println(memberDTO);
		}
	}

}
