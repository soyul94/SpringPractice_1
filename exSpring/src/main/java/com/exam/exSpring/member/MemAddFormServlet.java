package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/member/addform.do")
public class MemAddFormServlet extends HttpServlet { 
	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemAddFormServlet 실행 ! ");

		//RequestDispatcher rd= req.getRequestDispatcher("");
		//rd.forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/view/member/MemAddForm.jsp").forward(req, resp);
		
/*		
		req.setCharacterEncoding("UTF-8");//post방식으로 전송되는 한글파라미터 인코딩	
		resp.setContentType("text/html; charset=UTF-8");
				
		PrintWriter out = resp.getWriter();	
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">"); 
		out.println("<title>회원 추가</title>");
		out.println("<style> .center{text-align:center;}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>MEMBER_TABLE</h2></b>");
		out.println("<p>DB에 인스턴스 추가하기</p>");		
										//명령어로 주소 찾아올 수 이씀
		out.println("<form action= \"" + req.getContextPath() + "/member/add.do\" method=\"post\">"); // \"가 아니라 그냥 '로 써도 가능
		out.println("ID 		: <input type = \"text\" name=\"memId\"/><br>");
		out.println("PASSWORD 	: <input type = \"password\" name=\"memPW\"/><br>");
		out.println("NAME 		: <input type = \"text\" name=\"memName\"/><br>");
		out.println("POINT 		: <input type = \"text\" name=\"memPoint\"/><br>");
		out.println("<input type=\"submit\" value='등록'/>");
		out.println("</form>");
		out.println("<button onclick=\"location.href='./list.do'\">목록가기</button>");
			
		out.println("</body>");
		out.println("</html>");
*/
	}

}
