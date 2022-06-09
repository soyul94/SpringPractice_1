package com.exam.exSpring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * Handles requests for the application home page.
 */

// 서블릿에서 수행하던 주요 작업을 모두 컨트롤러에서 처리할 수 있다
//- 요청 파라미터 값 받기
//- JSP에서 사용할 데이터를 요청객체에 저장
//- 화면을 출력할 JSP파일로 이동(forward)

// @Controller : 웹 요청을 받아서 실행되는 클래스임을 의미
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//@RequestMapping : 웹요청을 받아서 실행되는 코드를 담고 있는 메서드
//					path 또는 value값으로 요청 경로(주소) 설정
//					method 값으로 요청방식 설정
	@RequestMapping(value = "/home.do", method = RequestMethod.GET) //순수jsp에서 서블릿 하나의 역할
	public String home
		(Locale locale, //Locale : 현재 요청하는 지역의 데이터 //Model : jsp와 공유해야하는 정보
		@RequestParam("myId") int id, //@RequestParam("myId") String id: "myId"로 들어온 파라미터를 String id 변수에 저장해서 메서드의 인수로 받음
									  //파라미터 값은 모두 String이다. 그러나 자바에서 int로 받으면 자동으로 형변환을 해준다.
		String myId, 				  // 파리미터명과 변수명이 동일하면 어노테이션 생략가능 -> String myId라고 하면 알이서 매칭함
		MyVO vo, 		//MyVO vo: 사용자가 정의한 클래스 타입인 경우, 객체의 속성(멤버변수)명과 동일한 이름의 파라미터값을 자동 저장
						//마찬가지로 model에 클래스명의 첫글자만 소문자로 변경한 이름으로 저장된다 (객체명이 아닌 클래스명 !)
		@ModelAttribute("modelVo") MyVO mvo,  //파라미터로 받은 mvo객체를 "modelVO"라는 이름으로 모델에 저장(추가)
											  //메소드 실행문에서 model.addAttribute("modelVo",mvo) 해준 것과 동일
											  //@ModelAttribute를 생략하면, 클래스명의 첫글자만 소문자로 변경한 이름으로 모델에 저장함
		Map map,
		ModelMap modelMap,
		Model model) { 
		
		//인자로 받은 Model, Map, ModelMap 타입의 객체에 key-value 형태로 데이터를 저장하면 JSP에서 ${key} 표현으로 사용가능하다.
		
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("id : {}. ", id); //콘솔에 "INFO : com.exam.exSpring.HomeController - id : soyul. "라고 출력된다
		logger.info("myId : {}. ", myId); //->윗 줄과 동일한 결과 출력
		logger.info("vo.myId : {}. ", vo.getMyId()); 
		logger.info("vo.myName : {}. ", vo.getMyName()); 
		
		//각 객체 타입별 JSP로 값을 전달하는 방법
		model.addAttribute("modelVal","너부리");
		modelMap.addAttribute("modelMapVal","포로리");
		map.put("mapVal","보노보노");
		
		return "home";	//순수 서블릿에서 직접 forward하던 것을 return으로 대신함
		//핸들러(@RequestMapping 메서드)에서 문자열을 반환하면, 스프링은 그 문자열을 View이름으로 인식하고 (ViewResolver을 이용하여) 처리
		//servlet-context.xml에 보면 org.springframework.web.servlet.view.InternalResourceViewResolver을 사용한 것을 알 수 있음.
	}
	
	
//	스프링이 핸들러(@RequestMapping 메서드)를 실행시키면 핸들러의 실행결과로서 스프링에게 반환(제공)해야하는 정보
//	-> 모델 : 응답(화면출력)에 포함되어야하는 데이터. 즉, JSP에서 응답을 출력할 때 사용할 데이터 
//	   뷰  : 응답 출력을 담당하는 객체 == JSP파일
// 	이 때, 반환하는 방식
// 	(1)모델과 뷰를 하나의 객체에 담아서 반환 : 핸들러가 ModelAndView객체를 반환함(ModelAndView : 모델과 뷰가 함께 존재하는 객체)
	@RequestMapping("/useModelAndView.do")
	public ModelAndView useModelAndView(){
 		MyVO vo = new MyVO();
 		vo.setMyId("a001");
 		vo.setMyName("고길동");
 		
 		ModelAndView mav = new ModelAndView();
 		mav.addObject("myVo",vo); //vo객체를 "myVo"라는 이름으로 mav의 Model에 저장(추가)
 		//mav.setView(new InternalResourceView("/WEB-INF/views/test.jsp")); 
 		//setView("JSP파일 주소"): 스프링컨테이너에 정의된 ViewResolver를 거치지 않고 직접 객체를 생성하여 forward함
 		// InternalResourceView 객체는 지정한 경로의 파일로 이동(forward)시키는 뷰
 		mav.setViewName("test"); 
 		//setViewName("JSP파일 이름")스프링컨테이너에 정의된 ViewResolver이용하여 forward함. 그래서 JSP의 주소가 아니라 이름만 기입함
 		
 		return mav;
 	}
//	(2)뷰 정보(뷰 객체 또는 뷰 이름)만 반환하는 방법
//	모델정보는 인자로 받은 Model,ModelMap,Map 객체에 저장하면 JSP로 이동한다 -> 가장 널리 쓰임
	@RequestMapping("/useOnlyView_1.do")
	public View useOnlyView_1(Map<String, Object> map){
 		MyVO vo = new MyVO();
 		vo.setMyId("a001");
 		vo.setMyName("고길동");
 		
 		map.put("myVo",vo);
 		
 		//뷰 객체 자체를 반환
 		return new InternalResourceView("/WEB-INF/views/test.jsp");		
 	}
	@RequestMapping("/useOnlyView_2.do")
	public String useOnlyView_2(Map<String, Object> map){
 		MyVO vo = new MyVO();
 		vo.setMyId("a001");
 		vo.setMyName("고길동");
 		
 		map.put("myVo",vo);
 		
 		//뷰 이름만 반환
 		return "test";
 	}	
//	(3)모델 정보만 반환하는 방법
//	메소드가 반환한 객체를 클래스명의 첫글자만 소문자로 변경한 이름으로 모델에 저장(추가)
//	모델에 저장되는 이름을 지정하고 싶다면, @ModelAttribute("이름")을 사용
//	뷰정보를 반환하지 않으면, (컨텍스트패스를 제외한)요청주소에서 첫 /와 마지막 확장자를 제거한 문자열을 뷰 이름으로 사용
	@RequestMapping("/UseOnlyModel.do")//즉, 해당 메소드의 뷰 이름은 "/UseOnlyModel.do" -> UseOnlyModel 가 된다. 
	public MyVO useOnlyModel(){
 		MyVO vo = new MyVO();
 		vo.setMyId("a001");
 		vo.setMyName("고길동");
 		
 		return vo; //-> vo의 클래스 이름이 MyVO이므로 "myVO"라는 이름으로 모델에 저장함 
	}
		
}

