package com.exam.exSpring.bbs;


import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.exSpring.member.MemberVO;

/*
- 스프링의 철학 
Plain Old Java Object : 평범하고 옛날부터 쓰는 자바 오브젝트로 만들자 -> 특별한 라이브러리나 앱에 종속되지 않는 프레임을 만들자
						그래서 서블릿과 다르게 특수한 상속문구들이 없이 평범한 자바 클래스로 구현하는 것을 권장함
*/

@Controller
@RequestMapping("/bbs/") //해당 컨트롤러의 핸들러에서 공통적으로 쓰이는 요청주소를 지정해줌
public class BbsController  { //extends HttpServlet를 해줄 필요가 없음
	
	//왜 여기서는 전자정부랑 다르게 @resource 사용이 불가하지 ???
	@Autowired
	@Named("bbsServiceImpl")
	private BbsService bbsService;
	
/*	
	Spring 4.3버전 부터 추가된 메소드 -> HttpMethod 방식에 맞게 어노테이션으로 매핑 가능해짐
	@RequestMapping(value="요청주소", method=RequestMethod.GET) == @GetMapping(value = "요청주소")
	@RequestMapping(value="요청주소", method=RequestMethod.POST) == @PostMapping(value = "요청주소")
	
	get과 post 외에도 Put, Delete, Patch 등도 가능하다.
	(@PutMapping, @DeleteMapping, PatchMapping)
	
*/	
	@RequestMapping(value = "list.do")	//@GetMapping(value = "요청주소") //스프링 4.3 이후로 사용할 수 있음. value생략 가능
	public String bbsList(Model model) {
		System.out.println("bbsList 실행");
		
		List<BbsVO> vo= bbsService.selectBbsList();
		
		//System.out.println("sql 수행 완료");
		
		model.addAttribute("list", vo);
		
		return "bbs/bbsList";
	}
	
	@RequestMapping(value="detail.do")
	public String bbsDetail(String bbsNo, Model model ) {
		System.out.println("bbsDetail 실행");
		
		BbsVO vo = bbsService.selectBbs(bbsNo);
		
		model.addAttribute("vo",vo);
		
		return "bbs/bbsDetail";
	}
	
	
	//이렇게 기능이 집중되는 것은 좋지 못함. 
	@RequestMapping(value = "form.do")
	public String bbsForm(String menu, BbsVO vo, @SessionAttribute("loginUser") MemberVO user, HttpSession session, Model model) {
		System.out.println("bbsForm 실행");
		System.out.println(vo.getBbsNo());
		System.out.println(menu);
		
//		로그인 정보를 세션에서 꺼내옴
//		MemberVO user = (MemberVO)session.getAttribute("loginUser"); == @SessionAttribute("loginUser") MemberVO
//		@SessionAttribute 이 어노테이션은 4.3 이후에 등장한 것으로 사용에 주의 해야한다
		
		if(user!=null && user.getMemId()!=null && user.getMemId()!="")
		vo.setBbsWriter(user.getMemId());
		model.addAttribute("user",user);
		
		if(menu.equals("edit")) {
			BbsVO bbsUser = bbsService.selectBbs(Integer.toString(vo.getBbsNo()));	
			System.out.println(bbsUser.getBbsContent());
			model.addAttribute("bbsUser",bbsUser);
		}
//		else if(menu.equals("edit")) {
//			vo.
//		}
//		
		model.addAttribute("menu",menu);
		
		return "bbs/bbsForm";
	}
	
	@RequestMapping(value="add.do")
	public String bbsAdd(String menu, BbsVO vo, Model model) {
		System.out.println("bbsAdd 실행");
		
		System.out.println(vo.getBbsWriter());
		System.out.println(vo.getBbsTitle());
		System.out.println(vo.getBbsContent());
		
		int num = bbsService.insertBbs(vo);
		System.out.println(num);
		
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="edit.do")
	public String bbsEdit(String menu, BbsVO vo, Model model) {
		System.out.println("bbsEdit 실행");
		
		System.out.println(vo.getBbsTitle());
		System.out.println(vo.getBbsContent());
		
		int num=bbsService.updateBbs(vo);
		
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="delete.do")
	public String bbsDelete(String bbsNo, Model model) {
		System.out.println("bbsDelete 실행");
		
		int num=bbsService.deleteBbs(bbsNo);
		
		return "redirect:/bbs/list.do";
	}
}
