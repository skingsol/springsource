package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller   //http://localhost:8080/ 요청 응답 컨트롤러
@RequestMapping("/member")
public class MemberController {
	
	//GET + POST 둘다 응답
	//@RequestMapping(value="/login", method = RequestMethod.GET)   //http://localhost:8080/member/login
	
	//사용자 입력값 가져오기
	// 1) HttpServletRequest 사용하기
	// 2) 변수사용 : 변수명은 폼 태그 name 과 일치 // 폼 태그 name 과 일치하지 않을 때 @RequestParam 사용
	// 3) 바인딩 객체 사용
	
	
	
	@GetMapping("/login")
	public void loginGet(HttpServletRequest req) {
		log.info("login...");
		log.info("method "+req.getMethod());
//		return "/member/login";    //  리턴이 있다면 /WEB-INF/views/login.jsp
	}
	
	//@RequestMapping(value="/login", method = RequestMethod.POST)   //http://localhost:8080/member/login
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest req) {
//		log.info("login post...");
//		log.info("method "+req.getMethod());
//		// 사용자 입력값 id, password
//		System.out.println("id : "+req.getParameter("id"));
//		System.out.println("password : "+req.getParameter("password"));
//	}
	
	@PostMapping("/login")
	public void loginPost(@RequestParam("userid") String id,String password) {
		log.info("login post...");
		// 사용자 입력값 id, password
		System.out.println("id : "+id);
		System.out.println("password : "+password);
	}
	
	
	//@RequestMapping("/register")  ////http://localhost:8080/member/register
	@GetMapping("/register")
	public void registerGet() {
		log.info("register...");
//		return "/member/register";  // 리턴이 있다면  /WEB-INF/views/register.jsp
	}
}
