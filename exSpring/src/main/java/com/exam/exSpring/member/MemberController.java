package com.exam.exSpring.member;


import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
- 스프링의 철학 
Plain Old Java Object : 평범하고 옛날부터 쓰는 자바 오브젝트로 만들자 -> 특별한 라이브러리나 앱에 종속되지 않는 프레임을 만들자
						그래서 서블릿과 다르게 특수한 상속문구들이 없이 평범한 자바 클래스로 구현하는 것을 권장함
*/

@Controller
public class MemberController  { //extends HttpServlet를 해줄 필요가 없음
	
	@Autowired
	private MemberService memberService;
	//MemberDao memberDao = MemberDaoMybatis.getMemberDaoMybatis();
	//스프링은 기본이 싱글톤이라 위 처럼 따로 싱글톤 형식의 클래스를 만들지 않아도 된다.
	
											//get방식의 요청에만 작동한다
	@RequestMapping(value="/member/list.do", method=RequestMethod.GET) //@WebServlet("/member/list.do")를 대체함
	public String memberList(Map<String,Object> map) {		
		//HttpServletRequest req, HttpServletResponse resp 는 jsp로 보내기 위한 작업을 했기 때문에 스프링에서 필요가 없음
		//Model	이 얘들이 해주는걸 한번에 수행함					
		
		// 클라이언트가 "member/list.do"로 요청을 보내면 웹브라우저에 회원목록이 출력되도록 구현
		System.out.println("memberList 실행 ! ");
		
		List<MemberVO> list = memberService.selectMemberList();
		map.put("memList", list);
		
		return "member/memList";
	}
	
	
	@RequestMapping(value="/member/add.do", method=RequestMethod.GET) 
	public String memberAddGet() {
		System.out.println("memberAddGet 실행 ! ");
		return "member/memAddForm";
	}
	
	@RequestMapping(value="/member/add.do", method=RequestMethod.POST)   
	public String memberAddPost(MemberVO vo) {
		System.out.println("memberAddPost 실행 ! ");		
		
		int num= memberService.insertMember(vo);
		System.out.println(num);
		
		return "redirect:/member/list.do"; 
		//뷰 이름에 redirect: 접두어를 사용하여 (포워드가 아닌 리다이레트임을 표시함)
	}
	
	
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.GET)   
	public String memberEditGet(String memId, Model model) {
		System.out.println("memberEditGet 실행 ! ");
		
		MemberVO vo = memberService.selectMember(memId);
		
		//HttpSession session = (HttpSession)model.getClass();
		//MemberVO user= (MemberVO)session.getAttribute("loginUser");
		
//		System.out.println(user.getMemId().equals(vo.getMemId()));
//		
//		if(user.getMemId().equals(vo.getMemId())){
			model.addAttribute("identification", "true");
//		}
//		else {
//			model.addAttribute("identification", "false");
//		}
		
		model.addAttribute("memVo", vo);
		
		return "member/memEditForm";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.POST)   
	public String memberEditPost(MemberVO vo) {
		System.out.println("memberEditPost 실행 ! ");		
		
//		if(user.getMemId().equals(vo.getMemId())){ //로그인회원아이디와 변경할 회원아이디가 같으면
//			vo.setMemId(req.getParameter("memId"));
//			//vo.setMemPW(req.getParameter("memPW"));
//			vo.setMemName(req.getParameter("memName"));
//			vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
			int num= memberService.updateMember(vo);		
//		}
//		else {
//			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경 불가"); //예외처리
//		}
		
		return "redirect:/member/list.do";
	}
	
	
	
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)   
	public String memberDeleteGet(MemberVO vo) {
		System.out.println("memberDeleteGet 실행 ! ");		
		
		int num= memberService.deleteMember(vo.getMemId());
		
		//resp.sedRedirect("이동할 주소") : 명령을 사용하여 웹브라우저에게 특정 주소로 이동하라는 명령을 담은 응답을 전송
		
		return "redirect:/member/list.do";
	}
	
	
	
	@RequestMapping(value="/member/login.do", method = RequestMethod.GET) 
	public String memberLoginGet() {
		System.out.println("memberLoginGet 실행 ! ");		
		return "member/memberLogin";
	}
	
	@RequestMapping(value="/member/login.do", method = RequestMethod.POST)  
	public String memberLoginPost(MemberVO user, HttpSession session, Model model){
		System.out.println("memberLoginPost 실행 ! ");		
		
		MemberVO result = memberService.selectLoginMember(user);
		
		if(result==null) {//로그인 실패한 경우 : 다시 로그인 폼으로 이동함.
			return "redirect:/member/login.do"; //get으로 요청함
		}else {//로그인 성공한 경우
			//스프링은 HttpSession개체를 지원해준다. 인자로 받기만 하면 됌.
			session.setAttribute("loginUser", result);	// 로그인 성공한 사용자 정보를 세션에 'loginUser'라는 이름으로 저장
			
			return "redirect:/member/list.do";
		}
	}
		
	@RequestMapping(value="/member/logout.do", method = RequestMethod.GET)
	public String memberLogoutGet(HttpSession session) {
		System.out.println("memberLogoutGet 실행 ! ");
		
		// session의 속성값을 삭제하는 방법
		//1. 속성값에 null을 set함
		session.setAttribute("loginUser", null); 
		//2. 속성 자체를 remove메소드를 이용하여 지워버림
		session.removeAttribute("loginUser");
		//3. 세션객체 전체를 초기화(삭제 후 재생성)
		session.invalidate(); 
		//현재 생성한 세션객체를 유요하지 않다고 선언해주는 것. 그래서 톰캣이 유요하지 않는 것을 지우고 새로 만틈
		
		return "redirect:/member/list.do";
	}

}
