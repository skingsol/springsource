package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	//insert.jsp 보여주기(get방식) => http://localhost:8080/book/insert + GET
//	@GetMapping("/insert")
//	public String insertForm() {
//		log.info("도서 정보 추가 폼 요청");		
//		return "/product/insert";
//	}
//	
//	@PostMapping("/insert")
//	public String insertPost(BookDTO dto) {
//		log.info("도서 정보 추가");
//		//입력값 가져오기 - HttpServleRequest, 변수, ~~DTO 셋중에 1개 사용하기
//		//서비스 작업하기
//		try {
//			boolean insertFlag = service.insert(dto);
//			if(insertFlag) {
//			// 입력 성공 시 목록 보여주기
//				return "redirect:/book/list";
//			}else {
//				// 입력 실패 시 insert.jsp 보여주기
//				return "/product/insert";
//			}			
//		} catch (Exception e) {
//			System.out.println("오류");
//			return "/product/insert";
//		}
//		
//	}
//	
//	
//	//http://localhost:8080/book/list + GET => list.jsp 보여주기
//	@GetMapping("/list")
//	public String listForm(Model model) {
//		log.info("도서 정보 목록 요청");
//		
//		//사용자가 작성한값 가져오고
//		//service 작업
//		List<BookDTO> list = service.getList();
//		
//		//list 에 담긴 정보를 list.jsp에 보여주고 싶다면?
//		model.addAttribute("list", list); // == request.setAttribute()
//		
//		return "/product/list";
//	}
//	
//	//http://localhost:8080/book/read?code=1001 + GET
//	@GetMapping("/read")
//	public String readGet(int code, Model model) {
//		log.info("read 요청"+code);
//		
//		BookDTO dto = service.get(code);
//		model.addAttribute("dto",dto);
//		
//		return "/product/read";
//	}
	
	//http://localhost:8080/book/insert + GET
	//    / book/insert.jsp 보여주기
	@GetMapping("/insert")
	public void insertForm() {
		log.info("도서 정보 추가 폼 요청");
	}
	
	@PostMapping("/insert")
	public String insertPost(BookDTO dto) {
		log.info("도서 정보 추가");
		//입력값 가져오기 - HttpServleRequest, 변수, ~~DTO 셋중에 1개 사용하기
		//서비스 작업하기
		try {
			boolean insertFlag = service.insert(dto);
			if(insertFlag) {
			// 입력 성공 시 목록 보여주기
				return "redirect:/book/list";
			}else {
				// 입력 실패 시 insert.jsp 보여주기
				return "/book/insert";
			}			
		} catch (Exception e) {
			System.out.println("오류");
			return "/book/insert";
		}
		
	}
	
	
	//http://localhost:8080/book/list + GET => /book/list.jsp 보여주기
	@GetMapping("/list")
	public void listForm(Model model) {
		log.info("도서 정보 목록 요청");
		
		//사용자가 작성한값 가져오고
		//service 작업
		List<BookDTO> list = service.getList();
		
		//list 에 담긴 정보를 list.jsp에 보여주고 싶다면?
		model.addAttribute("list", list); // == request.setAttribute()
		
	}
	
	//http://localhost:8080/book/read?code=1001 + GET => book/read.jsp
	@GetMapping("/read")
	public void readGet(int code, Model model) {
		log.info("read 요청"+code);
		
		BookDTO dto = service.get(code);
		model.addAttribute("dto",dto);
		
	}
	
}
