package com.my.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostMapping("login")
	@ResponseBody //클라이언트한테 json의 형태로 응답하기 위해서 사용
	public Map login(String id, String pwd, HttpSession session) {
		Map<String, Object>map = new HashMap<>();
		map.put("status",0);
		session.removeAttribute("loginInfo");

		//비지니스 로직 호출
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo",id);
		}catch(FindException e) {
			e.printStackTrace();
		}
		return map;
	}

	@PostMapping("signup")
	@ResponseBody
	public Map signup (Customer c) {
		Map<String, Object> map = new HashMap<>();
		map.put("status",0);
		map.put("msg", "가입실패");
		System.out.println(c.getId() + c.getPwd() + c.getAddress() + c.getName() + c.getBuildingno() );
		try {
			service.signup(c);
			map.put("status",1);
			map.put("msg", "가입성공");
		}catch (AddException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@PostMapping("iddupchk")
	@ResponseBody
	public Map iddupchk (String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("status",0);
		map.put("msg", "이미 사용중인 아이디가 있습니다");
		try {
			service.iddupchk(id);
		}catch (FindException e) {
			map.put("status", 1);
			map.put("msg", "사용가능한 아이디 입니다");
		}
		return map;
	}

	@PostMapping("loginstatus")
	@ResponseBody
	public Map loginstatus(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("status",0);
		map.put("msg", "로그아웃 상태");
		if(session.getAttribute("loginInfo")== null) {
			map.put("status", 0);
			map.put("msg", "로그아웃 상태");
		}else {
			map.put("status", 1);
			map.put("msg", "로그인 상태");
		}
		return map;
	}
	
	@PostMapping("logout")
	@ResponseBody
	public Map logout(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		session.removeAttribute("loginInfo");
		map.put("status", 0);
		map.put("msg", "로그아웃 되었습니다");
		return map;
	}
		
	

}

