package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer url = request.getRequestURL();
      String contextPath = request.getContextPath();
      String servletPath = request.getServletPath();
      System.out.println("url=" + url); //http://localhost:8888/back/request
      System.out.println("contextPath=" + contextPath); // /back
      System.out.println("servletPath=" + servletPath); // /request
      
      //http://localhost:8888/back/request?opt=add 요청일 경우 opt값은 add
      //http://localhost:8888/back/request?opt=    요청일 경우 opt값은 ""   이름은 있는데 값이 없으면 빈 문자열
      //http://localhost:8888/back/request        요청일 경우 opt값은 null 요청전달데이터 자체가 없으면 null 
      String opt = request.getParameter("opt");
      System.out.println("요청전달데이터 opt=" + opt); //add
      
//      if(opt.equals("add")){   //nullpointerexception발생 에러날 수 있음  -> 제일 안좋은 코드
//      if(opt != null && opt.equals("add")){   //너무 복잡함 -> 안좋은 코드
      if("add".equals(opt)) {
      System.out.println("등록작업을 선택했습니다");
      }   
      
      //http://localhost:8888/back/request?c=c1&c=c2 요청일 경우 c값은 c1, c2
      //http://localhost:8888/back/request?c=       요청일 경우 cArr.length는 0
      //http://localhost:8888/back/request          요청일 경우 cArr이 null
      String[] cArr = request.getParameterValues("c");
      if(cArr != null) {
    	  for(String c: cArr) {
    		  System.out.println("요청전달데이터 c=" + c);
    	  }
      }
   }
}
    	  
      
