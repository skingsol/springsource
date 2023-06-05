package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.SpUserDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired
	private MemberService service;
	
	// 회원가입
	@GetMapping("/register")
	public void registerGet() {
		log.info("회원가입 폼 요청");
	}
	
	// 회원가입 성공시 index 이동
	//          실패시 register 이동
	@PostMapping("/register")
	public String registerPost(SpUserDTO dto) {
		log.info("회원가입 요청" + dto);
		
		String path = service.register(dto) ? "redirect:/" : "/security/register";
		return path;
	}
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 폼 요청");
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_USER')") //security-context.xml 의 intercept-url 과 같은 역할
	@GetMapping("/userpage")
	public void userPage() {
		log.info("유저페이지 요청");
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@GetMapping("/adminpage")
	public void adminPage() {
		log.info("어드민 페이지 요청");
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", "아이디나 비밀번호를 확인해 주세요");
		return "/security/login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/security/denied";
	}
	
	@ResponseBody
	@GetMapping("/auth")
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
