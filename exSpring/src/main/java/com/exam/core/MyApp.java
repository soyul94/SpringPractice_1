package com.exam.core;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//어노테이션의 이름을 생략하면 원본 클래스 이름의 첫글자만 소문자로 바꾸어 저장된다.
@Component(value = "MyApp")//Component 어노테이션에는 value값 하나 뿐이라 속성명을 생략할 수 있다 
public class MyApp {
	
	//스프링에 등록된 객체들 중에서 이 변수 타입에 맞는 객체를 주입(저장)
	@Autowired//스프링에 등록된 객체들 중 자동으로 등록하라는 어노테이션 @Inject(비교적 최근버전)와 동일, //@Resource(구버전) : @Resource로 지정된 이름을 가져옴.
	//타입이 맞는 객체가 여러개 있는 경우, 그 중 이름이 일지하는 객체를 주입
	@Named("MyServiceKo")//자동으로 등록할 객체의 이름 //@Qualifier 어노테이션으로도 가능함(단, @Qualifier로 선언된 클래스만 가능.)
	private MyService myService;
		
	public MyService getMyService() {
		return myService;
	}
	public void setMyService(MyService myService) {
		this.myService = myService;
	}


	public void say() {
		System.out.println(myService.getHelloMsg());
		System.out.println(myService.getByeMsg());
	}
}
