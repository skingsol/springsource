package com.spring.dependency;

public class SonySpeaker implements Speaker {

	public SonySpeaker() {
		System.out.println("SonySpeaker 인스턴스 생성");
	}
	
	@Override
	public void volueUp() {
		System.out.println("SonySpeaker Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker Volume Down");
	}

}
