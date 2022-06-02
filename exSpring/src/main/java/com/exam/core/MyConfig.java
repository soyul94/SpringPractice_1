package com.exam.core;
//<!-- 최근 들어서 순수 xml 파일을 작성하는 것이 번거로워 순수 java로 만드는 추세가 증가하고 있다. -->

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 설정내용을 담고 있는 클래스임을 표시하는 어노테이션 ->xml처럼 작동함
@ComponentScan(basePackages= {"com.exam.core"})//<context:component-scan base-package="com.exam.core">와 동일한 기능
public class MyConfig {							// 각 클래스 마다 써줘야하는 어노테이션도 동일하다.
	
	//@Bean의 이름을 생략하는 경우 메소드명과 동일하게 등록된다.
//	@Bean("myApp")	//xml에서 선언한 <bean>태그와 같은 기능
	public MyApp myApp() {
		
		MyApp myApp = new MyApp();
		myApp.setMyService(myServiceEn()); //myServiceEn()메서드가 등록한 객체를 주입
		return myApp;
	}
//	@Bean("myServiceKo")
	public MyService myServiceKo() {
		return new MyServiceKo();
	}
//	@Bean("myServiceEn")
	public MyService myServiceEn() {
		return new MyServiceEn();
	}
}

//최근 흐름은 이와 같은 자바설정파일을 쓰는 추세지만 수업시간에는 스프링레거시에 주로 사용되는 xml파일을 사용할 것.
