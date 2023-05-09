package com.spring.dependency;

public class SamsungTV implements TV {
	
	private SonySpeaker speaker; // has-a (포함)관계
		
	//매개변수를 받지 않는 생성자 : default 생성자
	public SamsungTV() {
		
	}
	
	//매개변수를 받는 생성자
	public SamsungTV(SonySpeaker speaker) {
		super();
		this.speaker = speaker; //멤버 변수 초기화
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
