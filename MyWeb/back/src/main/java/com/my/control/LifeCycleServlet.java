package com.my.control;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LifeCycleServlet() {
    	System.out.println("LifeCycleServlet의 생성자 호출됨");
//    	ServletContext sc = this.getServletContext();
//    	String developer = sc.getInitParameter("developer"); //nullpointexception발생
//    	System.out.println(developer);
    }
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //ServletContext객체를 참조
		System.out.println("LifeCycleServlet의 init() 호출됨");
		ServletContext sc = this.getServletContext();
		String developer = sc.getInitParameter("developer"); //객체 생성시마다 초기화가 필요하면 init메서드에서
    	System.out.println(developer);
    	
    	String fileName = this.getInitParameter("fileName"); //servlet에서도 getInit사용 헷갈리지말기
    	System.out.println("fileName");
    	//변수로 안만들고 xml에서 값을 가져와서 getinitparameter로 가져오는지! 
    	//: 변경사항이 있을 때 class를 다시 열어서 코드를 손대고 다시 컴파일 해야하는데 
    	//잘못 손대면 잘 만들어져 있는 class 망가지게 되고 재컴파일 후 재배포 할 때에 걸리는 시간도 많이 걸림 
    	//재배포시에 에러나면 문제가 더 커진다(웬만하면 재컴파일 하지 않는게 좋음)
    	//설정 파일에서 변경될 수 있는 내용들은 설정파일에서 등록해서 사용
	}
	public void destroy() {
		System.out.println("LifeCycleServlet의 destroy() 호출됨");
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 service() 호출됨");
		super.service(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doGet() 호출됨");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("요청전달데이터 id=" + id + ", pwd=" + pwd);
	}
}
