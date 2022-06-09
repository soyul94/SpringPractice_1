package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
4월 28일 과제
1. 학생목록, 추가, 삭제 서블릿과 jSP파일을 구현(MVC패턴)
2. 학생정보 수정 기능 추가
*/
//웹브라우저에서 "http://localhost:8000/exweb/student/list.do"로 접속하면 학생목록이 출력되도록 구현
@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet { 
	//MemberDao memberDao = new MemberDAOJdbc(); //service가 몇번이 실행되던 1번만 생성되어야 하기 때문에 service 밖에 있어야한다.
	
	MemberDao memberDao = MemberDaoMybatis.getMemberDaoMybatis();
	
	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트가 "member/list.do"로 요청을 보내면 웹브라우저에 회원목록이 출력되도록 구현
		System.out.println("MemListServlet 실행 ! ");
		
		HttpSession session = req.getSession();
		MemberVO vo= (MemberVO)session.getAttribute("loginUser");// 로그인한 사용자정보를 가져오기
					//getAttribute() 메소드를 이용해서 꺼낸 값의 형태는 미정이므로 컴터가 알 수 있게 강제형변환을 해줘야한다.
		
		if(vo==null) { //로그인이 되지 않았을 때 강제로 로그인 페이지로 이동시킴
			resp.sendRedirect(req.getContextPath()+"/member/login.do");
		}
		else { //로그인이 되어 있으 경우 리스트를 출력함
			List<MemberVO> list = memberDao.selectMemberList(); //테이블을 읽는 것은 요청이 올 때마다 실행.
			req.setAttribute("memList", list);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/member/MemList.jsp");
			rd.forward(req, resp);//자기가 받은 요청객체와 응답객체를 설정된 jsp로 보냄
		}
		

		
/*
		req.setCharacterEncoding("UTF-8");//post방식으로 전송되는 한글파라미터 인코딩	
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();	
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">"); 
		out.println("<title>회원 목록</title>");
		out.println("<style> .center{text-align:center;}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>MEMBER_TABLE</h2></b>");
		out.println("<p>DB로부터 리스트를 받아와 출력하기</p>"); 
		
		out.println("<button onclick=\"location.href='./addform.do'\">회원추가</button>");
		out.println("<br>"); out.println("<br>");
		
		out.println("<table class=\"center\">");
		out.println("<tr>");
		out.println("<th style=\"width:70px\">ID</th>");	
		out.println("<th style=\"width:70px\">PASSWORD</th>"); 
		out.println("<th style=\"width:70px\">NAME</th>"); 
		out.println("<th style=\"width:70px\">POINT</th>");
		out.println("<th>DELETE</th>");
		out.println("</tr>");
		for(MemberVO e: list) {
			out.println("<tr>");
			out.println("<td>"+e.getMemId()+"</td>");
			out.println("<td>"+e.getMemPW()+"</td>");
			out.println("<td>"+e.getMemName()+"</td>");
			out.println("<td>"+e.getMemPoint()+"</td>");		
			out.println("<td>");
			out.println("<form action='"+req.getContextPath()+"/member/delete.do' method='get'>");
			out.println("<input type='hidden' name='memId' value='"+e.getMemId()+"'>");
			out.println("<input type='submit' value='삭제'/>");
			out.println("</form>");
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br>");
		
		out.println("</body>");
		out.println("</html>");
*/
	}

}
