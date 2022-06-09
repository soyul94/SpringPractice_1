package com.exam.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout.do")
public class MemLogoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemLogoutServlet doGet 실행 ! ");
		
		// session의 속성값을 삭제하는 방법
		HttpSession session = req.getSession();
		//1. 속성값에 null을 set함
		session.setAttribute("loginUser", null); 
		//2. 속성 자체를 remove메소드를 이용하여 지워버림
		session.removeAttribute("loginUser");
		//3. 세션객체 전체를 초기화(삭제 후 재생성)
		session.invalidate(); 
		//현재 생성한 세션객체를 유요하지 않다고 선언해주는 것. 그래서 톰캣이 유요하지 않는 것을 지우고 새로 만틈
		
		resp.sendRedirect(req.getContextPath()+"/member/login.do");
	}

}

