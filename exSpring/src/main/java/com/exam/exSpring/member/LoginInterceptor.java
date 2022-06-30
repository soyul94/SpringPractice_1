package com.exam.exSpring.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//핸들러인터셉터(HandlerInterceptor) : 다수의 컨트롤러 실행 전 후에 공통적으로 수행해야하는 작업을 처리
// HandlerInterceptorAdapter 클래스를 상속하여 구현

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//컨트롤러 실행 전에 실행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoginInterceptor : preHandle 실행");
		
		HttpSession session = request.getSession();
		MemberVO vo= (MemberVO)session.getAttribute("loginUser");// 로그인한 사용자정보를 가져오기
		//getAttribute() 메소드를 이용해서 꺼낸 값의 형태는 미정이므로 컴터가 알 수 있게 강제형변환을 해줘야한다.
		
		if(vo==null) { //로그인이 되지 않았을 때 강제로 로그인 페이지로 이동시킴
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			return false; // 이후에 진행될 작업을 못하게 함
		}
		
		//반환값은 이후 실행될 핸들러(컨트롤러,인터셉터)의 실행 여부를 결정
		return true;
	}
	
	//컨트롤러 실행 후, 뷰(JSP) 실행 전에 실행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor : postHandle 실행");
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//뷰 실행(JSP 실행 및 응답 출력)후 실행되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("LoginInterceptor : afterCompletion 실행");
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
