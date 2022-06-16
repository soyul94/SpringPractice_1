package com.exam.exSpring.member;


import java.util.List;
import java.util.Map;

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
	

}
