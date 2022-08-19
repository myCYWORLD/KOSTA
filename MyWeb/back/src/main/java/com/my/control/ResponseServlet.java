package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식설정: MINE(text/plain, text/html, application/json)
		response.setContentType("text/html;charset=UTF-8"); //응답형식 설정하면서 인코딩까지 같이 해주기
															//기본인코딩 ISO_88859_1
	
		PrintWriter out = response.getWriter(); //응답출력스트림 얻기
		//System.out의 자료형은 PrintStream
		out.print("<html>"); //응답출력스트림에 쓰기
		out.print("<body>");
		
		for(int i=1; i<=5; i++) {
			out.println("<h"+i+">"); //클라이언트에게 응답할 때 줄바꿈까지 응답하고 싶으면 println 아니라면 print
			out.println("제목"+ i);	 //네트워크 비용 줄이고 속도향상 원하면 print메서드 사용
			out.println("</h" + i+">");
		}
		out.print("</body>");
		out.print("</html>");   
	}
}