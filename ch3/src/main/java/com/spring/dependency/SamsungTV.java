package com.spring.dependency;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // 삼성티비 빈 생성해주세요 (SamsungTV 객체 생성)
public class SamsungTV implements TV {
	
	@Inject
	//@Autowired // 주입(스프링 컨테이너가 관리해야하는 빈 중에서  하나가 주입됨)
	@Qualifier("apple")
	private Speaker speaker; // has-a (포함)관계
		
	//매개변수를 받지 않는 생성자 : default 생성자
	public SamsungTV() {
		System.out.println("SamsungTV 인스턴스 생성 - default 생성자");
	}
	
	//매개변수를 받는 생성자
	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker; //멤버 변수 초기화
		System.out.println("SamsungTV 인스턴스 생성 - 인자 생성자");
	}
	
	
	public void setSpeaker(SonySpeaker speaker) {
		this.speaker = speaker;
	}
	
	

	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 파워 On");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 파워 Off");
	}
	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV - 볼륨 Up");
		speaker.volueUp();
	}
	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV - 볼륨 Down");
		speaker.volumeDown();
	}
}
