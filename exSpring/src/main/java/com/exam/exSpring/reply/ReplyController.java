package com.exam.exSpring.reply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.exSpring.bbs.BbsVO;
import com.exam.exSpring.member.MemberVO;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService; 
	
	@RequestMapping(value="/add.do",  method = RequestMethod.POST)
	@ResponseBody	//해당 메서드의 반환값을 그대로 응답으로 전송 -> return을 jsp경로로 받지 않고 단순 문자열로 출력
	public String addReply(ReplyVO replyVo, HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		
		if(user!=null && user.getMemId()!=null && user.getMemId()!="")
			replyVo.setRepWriter(user.getMemId());
		
		int num = replyService.insertReply(replyVo);
		
		return num+"개의 댓글 저장 성공";
	}
	
	@RequestMapping(value="/list.do",  method = RequestMethod.GET)
	@ResponseBody	//해당 메서드의 반환값을 그대로 응답으로 전송 -> return을 jsp경로로 받지 않고 단순 문자열로 출력
	public List<ReplyVO> listReply(ReplyVO replyVo, int repBbsNo, Model model) {
		
		//MemberVO user = (MemberVO)session.getAttribute("loginUser");
		
//		if(user!=null && user.getMemId()!=null && user.getMemId()!="")
//			replyVo.setRepWriter(user.getMemId());
//		
		List<ReplyVO> list = replyService.selectListReply(replyVo); // 결과 자체를 jsp 부분으로
		
		
		return list;
	}
	
//	@ResponseBody : 객체를 문자열로 변환 -> 이 때 
	
}
