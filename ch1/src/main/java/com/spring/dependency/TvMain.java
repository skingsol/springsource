package com.spring.dependency;

public class TvMain {
//	String str = "String"; // == new 와 같은 역할

	public static void main(String[] args) {
		
		//생성자를 사용한 멤버 변수 초기화
//		SonySpeaker speaker = new SonySpeaker();		
//		TV tv = new SamsungTV(speaker);
		
//		TV tv = new SamsungTV(new SonySpeaker()); //<=위의 두줄과 같음
	
		// setter 를 사용한 멤버 변수 초기화
		SamsungTV tv = new SamsungTV();		
		tv.setSpeaker(new SonySpeaker());		
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
//		TvMain obj = new TvMain();
//		obj.test();
	}
//	public void test() {
//		System.out.println(str);		
//		System.out.println(str.length());		
//	}
}
