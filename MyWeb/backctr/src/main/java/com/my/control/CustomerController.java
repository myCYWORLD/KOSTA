package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;

public class CustomerController implements Controller  {
	//private CustomerRepository repository;
	private CustomerService service;
	public CustomerController() {
		//repository = new CustomerOracleRepository();
		service = new CustomerService();
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String servletPath = request.getServletPath();

		if("/login".equals(servletPath)) { //로그인 작업
			return login(request, response);
		}else if("/signup".equals(servletPath)) { //가입작업 
			return signup(request, response);
		}else if ("/iddupchk".equals(servletPath)) {
			return iddupchk(request, response);
		}else if ("/loginstatus".equals(servletPath)) {
			return loginstatus(request, response);
		}else if ("/logout".equals(servletPath)) {
			return logout(request, response);
		}
		return null;
	}
	
	private String iddupchk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다.");
//		String result = null;
//		CustomerRepository repository = new CustomerOracleRepository();
		try {
			Customer c = service.iddupchk(id); //이미 존재하는 아이디인 경우
		}catch(FindException e) { //사용가능한 아이디인 경우
			map.put("status", 1);
			map.put("msg", e.getMessage());
		}
		return mapper.writeValueAsString(map);
	}
	

	private String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//요청 전달 데이터 얻기
		String id = request.getParameter("id");  
		String pwd = request.getParameter("pwd");
		//응답 결과
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object>map = new HashMap<>();
		map.put("status",0);
		String result = null; //"{\"status\": 0}";//실패
		//세션(클라이언트별 객체)얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		//비지니스 로직 호출
//		CustomerRepository repository = new CustomerOracleRepository();
//		try {
//			//repository.selectByIdAndPwd(id, pwd);
//			//result = "{\"status\": 1}"; //성공
//			//=> 위의 repository.selectByIdAndPwd(id, pwd); 한줄 코드와 아래 두 줄 코드는 같음
//			//재사용성을 높이기 위해 아래 두 줄 코드로 작성
//			Customer c = repository.selectById(id); //id고객에 만족하는 db의 비번을 가져와서 로그인
//			if(c.getPwd().equals(pwd)) {  //로그인 성공
//				map.put("status", 1);
//				session.setAttribute("loginInfo",id);
//			} //id에 만족하는 고객은 있으나 비번이 틀렸을 경우(로그인 실패)
//		}catch(FindException e) {
//		}
		
		//service layer 이용해서 코드 바꾸기 => 서비스 메서드 호출(id와 pwd값 호출)
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo",id);
			//					("loginInfo",c); 
			//로그인된 아이디 뿐만 아닌 다른 정보들을 세션에 관리하고 싶다면 객체를 넣어도 됨
		}catch(FindException e) {
			
		}
		result = mapper.writeValueAsString(map);
		return result;
	}

	private String signup(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr"); //상세주소
		String buildingno = request.getParameter("buildingno");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		//"{\"status\":0, \"msg\": \"가입실패\"}";
		map.put("status",0);
		map.put("msg", "가입실패");
		String result  = null;

//		CustomerRepository repository = new CustomerOracleRepository();
		Customer customer = new Customer(id, pwd, name, addr, 1, buildingno);
		try {
			service.signup(customer);
			//result = "{\"status\":1, \"msg\": \"가입성공\"}";
			map.put("status",1);
			map.put("msg", "가입성공");
		}catch (AddException e) {
			
		}
		result = mapper.writeValueAsString(map);
		return result;
	}
	
	private String loginstatus(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginInfo");
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object>map = new HashMap<>();
		
		String result = null;
		map.put("status",0);
//		map.put("msg", "로그아웃 상태");
		
		if(loginedId == null) {
			map.put("status", 0);
//			map.put("msg", "로그아웃 상태");
		}else {
			map.put("status", 1);
//			map.put("msg", "로그인 상태");
		}
		return mapper.writeValueAsString(map);
//		return result;
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo"); //세션객체는 존재, 속성제거
//		String result = ("로그아웃 되었습니다");
		//return '';  => 응답은 하되 내용이 비어있다
		return null; //응답은 하되 응답 내용 자체가 없이 응답하겠다	
		}
}
//로그인작업
//가입작업
//key와 value로 구분하는 건 좋으나 이걸 세분화 시키는 작업까지 하기에는 쉽지 않음 -> 세분화 시키고 싶으면 xml 파일을 만드는게 일반적인 관례
