package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

	@Slf4j
	@Controller  //@Component 자식
	@RequestMapping("/board") //option,    BasicController 는   http://localhost:8080/board/** 요청에 응답하는 컨트롤러야
	public class BoardController {
		
		
		//@RequestMapping(value="/read", method = RequestMethod.GET) //    http://localhost:8080/board/read + GET
		@GetMapping("/read")
		public void read() {		
			log.info("read 요청");		 
		}
		
		//@RequestMapping(value="/register", method = RequestMethod.GET) //    http://localhost:8080/board/register + GET
		@GetMapping("/register")
		public void register() {		
			log.info("register 요청");		 
		}
		
		//@RequestMapping(value="/modify", method = RequestMethod.GET) //    http://localhost:8080/board/modify + GET
		@GetMapping("/modify")
		public void modify() {		
			log.info("modify 요청");		 
		}		
		
		//@RequestMapping(value="/remove", method = RequestMethod.GET) //    http://localhost:8080/board/remove + GET
		@GetMapping("/remove")
		public void remove() {		
			log.info("remove 요청");		 
		}
}
