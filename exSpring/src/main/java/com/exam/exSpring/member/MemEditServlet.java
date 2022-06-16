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

//2022.05.26 과제
//1. 변경하려는 회원아이디가 로그인한 회원의 아이디가 아닌 경우 변경이 불가하도록 구현
//	-> 이러한 보안은 자바에서 해야한다. (자바스크립트나 jsp는 불가)
//2. 회원정보변경 화면에서도 화면의 회원아이디가 로그인한 회원의 아이디와 다르다면 
//   이름과 포인트 값이 변경 불가능하고 버튼도 출력되지 않도록 구현.


@WebServlet("/member/edit.do")
public class MemEditServlet extends HttpServlet { 
	MemberDao memberDao = MemberDaoMybatis.getMemberDaoMybatis(); //service가 몇번이 실행되던 1번만 생성되어야 하기 때문에 service 밖에 있어야한다.
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemEditServlet 실행 ! ");
		
		String memId = req.getParameter("memId");
		MemberVO vo = memberDao.selectMember(memId);
		
		HttpSession session = req.getSession();
		MemberVO user= (MemberVO)session.getAttribute("loginUser");
		
		System.out.println(user.getMemId().equals(vo.getMemId()));
		
		if(user.getMemId().equals(vo.getMemId())){
			req.setAttribute("identification", "true");
		}
		else {
			req.setAttribute("identification", "false");
		}
		
		req.setAttribute("memVo", vo);
		req.getRequestDispatcher("/WEB-INF/view/member/MemEditForm.jsp").forward(req, resp);
	}
	
	@Override  
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트가 "member/list.do"로 요청을 보내면 웹브라우저에 회원목록이 출력되도록 구현
		System.out.println("MemAddServlet 실행 ! ");		
		//req.setCharacterEncoding("UTF-8"); //post방식으로 전송되는 한글파라미터 인코딩	
		
		//웹브라우저에서 개발자도구를 이용하여 post방식으로 요청이 가능하기 때문에 jsp에서만 방지하면 안되고 직접적인 SQL문을 막도록 자바에서 설정해줘야한다.
		MemberVO vo= new MemberVO();
		
		HttpSession session = req.getSession();
		MemberVO user= (MemberVO)session.getAttribute("loginUser");
		
		
		if(user.getMemId().equals(vo.getMemId())){ //로그인회원아이디와 변경할 회원아이디가 같으면
			vo.setMemId(req.getParameter("memId"));
			//vo.setMemPW(req.getParameter("memPW"));
			vo.setMemName(req.getParameter("memName"));
			vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
			int num= memberDao.updateMember(vo);		
		}
		else {
			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경 불가"); //예외처리
		}
		
		//System.out.println(num);
		
		//resp.sedRedirect("이동할 주소") : 명령을 사용하여 웹브라우저에게 특정 주소로 이동하라는 명령을 담은 응답을 전송
		resp.sendRedirect(req.getContextPath()+"/member/list.do");


	}

}
