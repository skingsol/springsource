package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.PersonDTO;
import com.spring.service.PersonService;

public class PersonMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService) ctx.getBean("person");
		
		//삽입
//		PersonDTO inserPerson = new PersonDTO("hong123", "홍길동");
//		System.out.println(service.insertPerson(inserPerson)?"성공":"실패");
		
		List<PersonDTO> list = service.getRows();
		for(PersonDTO personDTO : list) {
			System.out.println(personDTO);
		}
		
		//수정
		System.out.println(service.updatePerson(new PersonDTO("kang123", "강동주"))?"성공":"실패");
		
		//삭제
		System.out.println(service.deletePerson("kim123")?"성공":"실패");
		
		//특정 사람 조회
		System.out.println(service.getRow("hong123"));
	}
}
