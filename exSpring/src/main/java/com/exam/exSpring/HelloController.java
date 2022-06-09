package com.exam.exSpring;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//2022.06.09 과제
//http://localhost:8000/exSpring/hello.do?x=3&y=4 로 요청을 보내면,
//화면에 
//	계산결과 : 3 + 4 = 7 
//	현재서버시간 : 2022년 6월 9일 16시 01분 (자바스크립드로 출력하는 것이 아니라 서버를 출력)
//로 출력되도록 구현 -> hello.jsp에 구현
//이 때, 파라미터는 MyValue객체로 받고, 계산결과는 MyValue객체의 sum변수에 저장
@Controller
public class HelloController {
	@RequestMapping(value = "hello.do" ,method = RequestMethod.GET)
	public String plusCalculator(MyValue value, Model model) {
		
		Date date = new Date();

		int x=value.getX();
		int y=value.getY();
		
		value.setSum(x+y);			
		
		model.addAttribute("time",date);
		model.addAttribute("modelMyValue",value);
		
		return "/HelloWorld/hello";
	}
}
