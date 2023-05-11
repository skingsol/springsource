package com.spring.console;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.BookDTO;
import com.spring.service.BookService;
import com.spring.service.BookServiceImpl;

public class BookMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		//Service 호출
		BookService service = (BookService) ctx.getBean("bookService");
		
//		//도서 추가
//		BookDTO insertDto = new BookDTO(1008, "나는고양이로소이다", "나쓰메소세키", 14800, null);
//		if(service.insertBook(insertDto)) {
//			System.out.println("입력성공");
//		}
		
//		//도서 수정
//		BookDTO updateDto = new BookDTO();
//		updateDto.setCode(1007);
//		updateDto.setPrice(14800);
//		if(service.updateBook(32500, 1006)) {
//			System.out.println("수정성공");
//		}

//		//특정 도서 조회
//		BookDTO row = service.getBook(1007);
//		System.out.println(row);

//		//도서 삭제
//		if(service.deleteBook(1008)) {
//			System.out.println("삭제성공");
//		}
//				
		//전체 도서 목록 가져오기
		//BookService service = new BookServiceImpl(new BookDAO());
		List<BookDTO> list = service.getBookList();
		
		for (BookDTO bookDTO : list) {
			System.out.println(bookDTO);
		}
	}

}
