package com.spring.dependency;

import org.springframework.stereotype.Component;

@Component("apple") // 빈 생성해주세요 == AppleSpeaker 객체 생성
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("AppleSpeaker 인스턴스 생성");
	}
	
	@Override
	public void volueUp() {
		System.out.println("AppleSpeaker Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker Volume Down");
	}

}
