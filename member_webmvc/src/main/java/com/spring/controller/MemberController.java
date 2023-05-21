package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	//login.jsp 보여주는 컨트롤러 작성
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 폼 요청");
	}
	
	@PostMapping("/login")
	public String loginPost(LoginDTO loginDTO,HttpSession session) {
		log.info("로그인 요청 "+loginDTO);
		
		AuthDTO authDTO = service.login(loginDTO);
		
		if(authDTO != null) {
			//session 로그인 정보 담기
			session.setAttribute("authDTO", authDTO);
			//index 이동
			return "redirect:/";			
		}else {
			return "redirect:/member/login";
		}
		
	}
	
	
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃 요청");
		
		session.removeAttribute("authDTO");
		
		//session 해제 index 이동
		return "redirect:/";
	}
	
	@GetMapping("/step1")
	public void stepGet() {
		// member/step1.jsp 보여주기
		log.info("약관 페이지 보여주기");		
	}
	
	@PostMapping("/step1")
	public String stepPost(boolean agree, RedirectAttributes rttr){
		log.info("약관동의 "+agree);
		// 약관동의 여부
		
		// true 인경우 register.jsp 보여주기
		if(agree) return "/member/register";
		// false 인 경우 step1.jsp 보여주기
		else {
			rttr.addFlashAttribute("check", false);
			return "redirect:/member/step1";
			//return "/member/step1"; // ==> View Resolver
		}		
	}
	
	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		log.info("회원가입 요청 "+ dto);
		
		//회원가입 성공 시 로그인 페이지 이동
		if(service.register(dto)) {
			return "redirect:/member/login";
		}else {
			return "/member/register";
		}
	}
	
	
	//중복 아이디 
	@PostMapping("/dupId")
	@ResponseBody //컨트롤러 작업이 완료될때 결과값으로 리턴시킴(뷰리졸버 동작시키지 않음)
	public String dupIdCheck(String userid) {
		log.info("중복 아이디 체크 "+userid);
		
		boolean idCheck = service.dupId(userid);
		
		if(idCheck) {
			return "true";  //   /WEB-INF/views/true.jsp 
		}else {
			return "false"; //   /WEB-INF/views/false.jsp 
		}		
	}
	
	@GetMapping("/leave")
	public void leaveGet() {
		// leave.jsp 보여주기
		log.info("회원 탈퇴 페이지 요청 ");
	}
	
	
	// 회원탈퇴 성공 시 index 이동
	@PostMapping("/leave")
	public String leavePost(LoginDTO loginDTO,HttpSession session) {		
		log.info("회원 탈퇴 요청 ");
		
		if(service.remove(loginDTO)) {
			session.invalidate();
			return "redirect:/";			
		}
		return "redirect:/member/leave";
	}
	
	@GetMapping("/changePwd")
	public void changePwdGet() {	
		
		
		
		// changePwd.jsp 보여주기
		log.info("비밀번호 변경 페이지 요청 ");
	}
	
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDTO,HttpSession session) {		
		log.info("비밀번호 변경 요청 "+changeDTO);
		
		if(changeDTO.passwordEquals()) {
			//현재 비밀번호 일치 확인
			//true : 비밀번호 변경 ==> session 제거 ==> 로그인 페이지 보여주기
			//false : 비밀번호변경 페이지 보여주기
			
			if(service.update(changeDTO)) {
				session.invalidate();
				return "redirect:/member/login";
			}			
		}			
		return "redirect:/member/changePwd";
	}
}










