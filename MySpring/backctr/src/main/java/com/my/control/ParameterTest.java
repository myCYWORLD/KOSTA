package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dto.Product;

@Controller
public class ParameterTest {
	@GetMapping("a")
	public void a() { 
	//외부에서 요청을 받아야 하므로 public으로 만들어줌, 
	//요청을 처리해줄 메서드(controller용 메서드)
		System.out.println("a메서드 호출");
	}
	
	@GetMapping("b") // http://localhost:8888/backctr/b?no=
	public void b(HttpServletRequest request) {
		System.out.println(request.getParameter("no")); 
	}
	@GetMapping("c") 
	public void c(HttpServletResponse response) throws IOException { 
	//필요한 exception들은 throws로 처리 해주면 jvm이 알아서 처리해줌
		response.sendRedirect("http://www.google.com"); 
	}
	
	@GetMapping("d") 
	public void d(HttpSession session) {
		System.out.println("세션 새로 생성여부:" + session.isNew()); 
	}
	
	@GetMapping("e") // http://localhost:8888/backctr/e?prodNo=&prodName=&prdoPrice=
	public void e(String prodNo, String prodName, int prodPrice) {	
		System.out.println("prodNo = " + prodNo);
		System.out.println("prodName = " + prodName);
		System.out.println("prodPrice = " + prodPrice);
	}
	
	@GetMapping("f") 
	// http://localhost:8888/backctr/f?prod_no=C0001&prdoName=아메리카노&prodPrice=10000
	//요청전달 데이터와 매개변수 다르게할 경우 @RequestParam을 이용해 name값을 줌
	// http://localhost:8888/backctr/f?prod_no=C0001&prodPrice=10000
	//요청전달 데이터에 매개변수를 안집어 넣고 싶을 경우 @RequestParam으로 required 값을 줌
	// http://localhost:8888/backctr/f?prod_no=C0001
	//spring에서 전달되지 않은 null값을 int타입으로 적용시키려면 전달되지 않았을때의 기본값을 String 타입으로 줘야함 
	//defaultValue를 0값으로 주면 0으로 초기화 됨 (null은 integer.parseint로 변환 못 함)
	public void f( @RequestParam(name = "prod_no") String prodNo, 
				   @RequestParam(required = false) String prodName, 
				   @RequestParam(required = false, defaultValue = "0") int prodPrice) {	
		System.out.println("prodNo = " + prodNo);
		System.out.println("prodName = " + prodName);
		System.out.println("prodPrice = " + prodPrice);
	}
	
	@GetMapping("g") 
	// http://localhost:8888/backctr/g?prodNo=C0001&prodName=아메리카노&prodPrice=1000
	// http://localhost:8888/backctr/g?prodNo=C0001
	public void g(Product p) {	
		System.out.println("prodNo = " + p.getProdNo());
		System.out.println("prodName = " + p.getProdName());
		System.out.println("prodPrice = " + p.getProdPrice());
	}
	
	@GetMapping("h")
	public void h(String[] arr)	{	
		for(String str: arr) {
			System.out.println(str);
		}
	}
	
	@PostMapping("i")
	public void i(@RequestBody List<Product> list) {
		for(Product p: list) {
			System.out.println(p);
		}
	}
}
