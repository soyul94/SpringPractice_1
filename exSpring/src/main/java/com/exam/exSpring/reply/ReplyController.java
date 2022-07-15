package com.exam.exSpring.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService; 
	
	@RequestMapping(value="/add.do",  method = RequestMethod.POST)
	@ResponseBody	//해당 메서드의 반환값을 그대로 응답으로 전송 -> return을 jsp경로로 받지 않고 단순 문자열로 출력
	public Map<String, Object> addReply(ReplyVO replyVo, HttpSession session, Model model){
		//반환형으로는 인터페이스를 사용하는 것이 좋다.
		System.out.println("addReply실행");
		
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		
		if(user!=null && user.getMemId()!=null && user.getMemId()!="")
			replyVo.setRepWriter(user.getMemId());
		
		int num = replyService.insertReply(replyVo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num",num);
		
		//응답을 받는 아작스의 데이터타입이 json이기 때문에 객체를 보내면 스프링이 자동으로 json으로 변환하여 전송한다.
		return map;
		
		
//아래방법은 아작스 데이터타입이 text이기 때문에 사용
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("num",num);
//		ObjectMapper mapper = new ObjectMapper(); //jackson라이브러리가 제공하는 객체. JAVA <-> JSON문자열 변환 담당
//		String jsonStr="실패";
//		try {
//			jsonStr = mapper.writeValueAsString(map);// JAVA객체->JSON문자열 변환
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}	
//				
//		return jsonStr ;
		
		//json형식으로 num 값 전송하기
//		return num+"개의 댓글 저장 성공";
	}
	
	
	@RequestMapping(value="/list.do",  method = RequestMethod.GET)
	@ResponseBody
	public List<ReplyVO> listReply(ReplyVO replyVo, int repBbsNo, Model model) {
		
		System.out.println("listReply실행");

		List<ReplyVO> list = replyService.selectListReply(repBbsNo); 
		
		
		return list;
	}
//	@ResponseBody : 객체를 문자열로 변환 -> 이 때 이 변환된 문자열을 xml이나 json으로 변환하여 ajax에서 사용하는 것
	
	@RequestMapping(value="/delete.do",  method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> deleteReply(ReplyVO replyVo, HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("loginUser");
		
		if(user!=null && user.getMemId()!=null && user.getMemId()!="")
			replyVo.setRepWriter(user.getMemId());
		
		System.out.println("deleteReply실행");

		int num = replyService.deleteReply(replyVo); 
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num",num);
		
		return map;
	}
	
}


/*
 * JSON 과 자바 크스립트의 차이점 : json은 문자열 표현에 쌍따옴표만 사용가능하며 객체의 속성명도 문자열로 표현해야한다.
 */
