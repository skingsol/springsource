package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.AddDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CalcController {
	
	@GetMapping("/add")
	public void addForm() {
		log.info("add 요청");
	}
	
	//사용자 입력값 가져오기
	// 1) HttpServletRequest 사용하기
	// 2) 변수사용 : 변수명은 폼 태그 name 과 일치 // 만약 폼 태그 name 과 일치하지 않을 때 @RequestParam 사용
	// 3) 바인딩 객체 사용
	
//	@PostMapping("/add")
//	public void addPost(AddDTO dto) {
//		log.info("add post 요청");
//		log.info("num1 "+dto.getNum1());
//		log.info("num2 "+dto.getNum2());
//		log.info("result "+(dto.getNum1()+dto.getNum2()));
//	}
	
	@PostMapping("/add")
	public String addPost(@ModelAttribute("add") AddDTO dto, @ModelAttribute("page") String page, Model model) {
		log.info("add post 요청");
		log.info("num1 "+dto.getNum1());
		log.info("num2 "+dto.getNum2());
		log.info("page "+page);
		int result = dto.getNum1()+dto.getNum2();
		log.info("result "+result); 
		// ==> result 값을 result.jsp에서 사용하고 싶다면?
		// ==> Model 객체를 사용 하기 == request.setAttribute() 와 같다
		// .jsp의 값을 DTO 로 받고 개별변수로 나눠 받아도 상관없다.
		
		model.addAttribute("result", result);
		//model.addAttribute("page", page);
		
		return "result"; //  /WEB-INF/views/result.jsp
	}
}
