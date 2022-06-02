package com.exam.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExSpring {
	public static void main(String[] args) {
		//스프링을 사용하지 않는 앱을 만들어보고 이를 스프링 사용앱으로 바꾸어보자
		
		//스프링 미사용
//		MyApp myApp = new MyApp();
//		myApp.setMyService(new MyServiceKo());
//		myApp.say();
//		myApp.setMyService(new MyServiceEn());
//		myApp.say();
		
		//스프링 사용				
								   // com/exam/core/context.xml 설정파일의 내용대로 스프링컨테이너를 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/exam/core/context.xml");//클래스 패스 기준이라 /exSpring/src/main/java는 생략가능
		
								  //MyConfig.java 설정파일의 내용대로 스프링컨테이너를 생성
//		ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		//모든 context를 다 담을 수 있도록 인터페이스로 선언.

		MyApp myApp= (MyApp)context.getBean("MyApp"); //스프링컨테이너의 프로퍼티에 MyApp라는 이름으로 등록된 객체를 가져오기 
		myApp.say();
		
		//스프링에 등록되어 있는 객체들이 목록
		for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName+" : "+ context.getBean(beanName).getClass().getName());
		}
		
	}
}
/*
	스프링컨테이너 : 객체를 생성하여 저장하는 보관소. 스프링의 본체. (=어플리케이션 컨텍스트 라고도 함.)
	
	-스프링의 핵심 기능
	IoC/DI : 제어의 역전(주입을 작성자가 아니라 스프링이 해서 제어가 역전되었다라고 정의)/의존성 주입(어떠한 객체가 결정되는 또 다른 객체를 작성자가 아닌 스프링이 주입한다.)
	AOP : 관점지향프로그래밍. 여러곳에 똑같은 코드를 반복해야할 경우 해당 코드를 따로 분리하여 원하는 지점에 끼워넣도록 하는 프로그래밍.
	
	Library : 프로그램의 메인 흐름(주도권)을 개발자(작성자)가 가지고 있음
	Framework : 프로그램의 메인 흐름을 개발자가 아닌 타인이 작성한 코드가 가지고 있음
 	
*/
