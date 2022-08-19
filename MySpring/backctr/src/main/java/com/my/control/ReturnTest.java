package com.my.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReturnTest {
	@GetMapping("a1")
	public ModelAndView a() {  
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("greeting", "HELLO");
		mnv.setViewName("/WEB-INF/jsp/a.jsp"); //setView만 있으면 복잡한 코드 없이 viewer로 이동
		return mnv;
	}
	@GetMapping("b1")
	public String b(Model model) {
		model.addAttribute("greeting", "안녕하세요");
		return "/WEB-INF/jsp/a.jsp"; //viewer이름을 return
		//return "/a1"; 
	}
	@GetMapping("c1") // viewname을 "/WEB-INF/jsp/c1.jsp"로 설정 (= return "/WEB-INF/jsp/c1.jsp")
	public void c() {
	}
	@GetMapping(value = "d1", produces = "text/plain;charset=UTF-8")
	@ResponseBody //ResponseBody를 추가하면 view 이름 형태가 아닌 응답 내용자체를 응답 (//너지금뭐하멘?)
	public String d() {
		String responseData = "너지금뭐하멘?";
		return responseData;  //기본 return 타입이 view =>요청 시 view이름으로 "너지금뭐하멘?"를 반환시킴
	}
}
