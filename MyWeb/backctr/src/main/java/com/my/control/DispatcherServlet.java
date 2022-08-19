package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
//		if("/login".equals(servletPath)) {
//			Controller controller = new CustomerController();
//			controller.execute(request,  response);
//		}else if("/signup".equals(servletPath)) {
//			Controller controller = new CustomerController();
//			controller.execute(request,  response);
//		}else if("/productlist".equals(servletPath)) {
//		}
		
		//지금 실행중인 웹프로젝트의 실제경로
		ServletContext sc = getServletContext();
		String envPath = sc.getRealPath("my.properties");
		System.out.println(envPath);
		
		//my.properties파일의 내용을 프로퍼티 이름과 값으로 JVM에 로드
		Properties env = new Properties();
		env.load(new FileInputStream(envPath));
		
		String clazzName = env.getProperty(servletPath);
		Controller control = null;
		String result = null;
		
		try {
			Class clazz = Class.forName(clazzName); //JVM에 클래스 파일(CustomerController.class)로드
			control = (Controller)clazz.newInstance(); //객체생성 (controller타입으로 다운캐스팅 해줘야함)
			result = control.execute(request, response); //execute 메서드 호출
			//Controller 까지 찾아오는 작업
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
