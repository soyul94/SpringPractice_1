package com.exam.exSpring.bbs;


import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
- 스프링의 철학 
Plain Old Java Object : 평범하고 옛날부터 쓰는 자바 오브젝트로 만들자 -> 특별한 라이브러리나 앱에 종속되지 않는 프레임을 만들자
						그래서 서블릿과 다르게 특수한 상속문구들이 없이 평범한 자바 클래스로 구현하는 것을 권장함
*/

@Controller
public class BbsController  { //extends HttpServlet를 해줄 필요가 없음
	
	//왜 여기서는 전자정부랑 다르게 @resource 사용이 불가하지 ???
	@Autowired
	@Named("bbsServiceImpl")
	private BbsService bbsService;
	
	@RequestMapping(value = "/bbs/list.do")
	public String bbsList(Model model) {
		System.out.println("bbsList 실행");
		
		List<BbsVO> vo= bbsService.selectBbsList();
		
		//System.out.println("sql 수행 완료");
		
		model.addAttribute("list", vo);
		
		return "bbs/bbsList";
	}
	
	@RequestMapping(value="/bbs/detail.do")
	public String bbsDetail(String bbsNo, Model model ) {
		System.out.println("bbsDetail 실행");
		
		BbsVO vo = bbsService.selectBbs(bbsNo);
		
		model.addAttribute("vo",vo);
		
		return "bbs/bbsDetail";
	}
	
	@RequestMapping(value = "/bbs/form.do")
	public String bbsForm(String menu, BbsVO vo, Model model) {
		System.out.println("bbsForm 실행");
		System.out.println(vo.getBbsNo());
		System.out.println(menu);
		
		if(menu.equals("edit")) {
			BbsVO user = bbsService.selectBbs(Integer.toString(vo.getBbsNo()));	
			System.out.println(user.getBbsContent());
			model.addAttribute("user",user);
		}
//		else if(menu.equals("edit")) {
//			vo.
//		}
		
		model.addAttribute("menu",menu);
		
		return "bbs/bbsForm";
	}
	
	@RequestMapping(value="/bbs/add.do")
	public String bbsAdd(String menu, BbsVO vo, Model model) {
		System.out.println("bbsAdd 실행");
		
		System.out.println(vo.getBbsWriter());
		System.out.println(vo.getBbsTitle());
		System.out.println(vo.getBbsContent());
		
		int num = bbsService.insertBbs(vo);
		System.out.println(num);
		
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="/bbs/edit.do")
	public String bbsEdit(String menu, BbsVO vo, Model model) {
		System.out.println("bbsEdit 실행");
		
		System.out.println(vo.getBbsTitle());
		System.out.println(vo.getBbsContent());
		
		int num=bbsService.updateBbs(vo);
		
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value="/bbs/delete.do")
	public String bbsDelete(String bbsNo, Model model) {
		System.out.println("bbsDelete 실행");
		
		int num=bbsService.deleteBbs(bbsNo);
		
		return "redirect:/bbs/list.do";
	}
}
