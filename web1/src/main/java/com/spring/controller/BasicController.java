package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller  //@Component 자식
@RequestMapping("/sample") //option,    BasicController 는   http://localhost:8080/sample/** 요청에 응답하는 컨트롤러야
public class BasicController {
	//컨트롤러 안에 일반 메소드 작성 가능
	//일반 메소드는 @RequestMapping 어노테이션을 붙이지 않으면 됨
	
	//@RequestMapping("/basic") //    http://localhost:8080/sample/basic + GET
	@GetMapping("/basic")
	public void basic() {
	//logger.info("basic......요청");
	 log.info("basic......요청");
	 
	 //컨트롤러 실행후(없을 수도 있음) ViewResolver 가 실행 됨
	 //리턴값이 있다면  /WEB-INF/views/basic.jsp
	 
	 // ViewResolver 는             /WEB-INF/view/sample/basic.jsp 으로 만들어서 결과 리턴
	 // [/WEB-INF/views/sample/basic.jsp]
	}
}
