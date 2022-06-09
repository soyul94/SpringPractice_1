package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/login.do")
public class MemLoginServlet extends HttpServlet { 
	MemberDao memberDao = MemberDaoMybatis.getMemberDaoMybatis(); 

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemLoginServlet doGet 실행 ! ");
		req.getRequestDispatcher("/WEB-INF/view/member/MemberLogin.jsp").forward(req, resp);
	}
	
	@Override  
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemLoginServlet doPost 실행 ! ");		
		//req.setCharacterEncoding("UTF-8"); //post방식으로 전송되는 한글파라미터 인코딩	
		
		//서버에 있는 아이디와 비밀번호를 입력했는지 알아볼 수 있는 메소드를 만들어보기
		MemberVO vo= new MemberVO();
		vo.setMemId(req.getParameter("userId"));
		vo.setMemPW(req.getParameter("userPW"));
		
		MemberVO result = memberDao.selectLoginMember(vo);
		
		//사용자별의 정보를 이용하고 저장하고자 할 때 사용 되는 것이 session
		if(result==null) {//로그인 실패한 경우 : 다시 로그인 폼으로 이동함.
			resp.sendRedirect(req.getContextPath()+"/member/login.do");
		}else {//로그인 성공한 경우
			HttpSession session= req.getSession();		// 현재 요청(을 보낸 사용자)가 속한 세션객체 가져오기
			session.setAttribute("loginUser", result);	// 로그인 성공한 사용자 정보를 세션에 'loginUser'라는 이름으로 저장
			
			//resp.sedRedirect("이동할 주소") : 명령을 사용하여 웹브라우저에게 특정 주소로 이동하라는 명령을 담은 응답을 전송
			resp.sendRedirect(req.getContextPath()+"/member/list.do"); 
		}
	}
	
}
