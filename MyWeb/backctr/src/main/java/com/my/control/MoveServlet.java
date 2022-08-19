package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;

/**
 * Servlet implementation class MoveServlet
 */
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		if("forward".equals(opt)) {
			//요청 속성(속성명: 'test', 값 : 상품객체 추가)
			Product sample = new Product("F0001", "샌드위치", 2000);
			request.setAttribute("test", sample);
			
			//---------FORWARD 전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE FORWARD");
			String path = "/iddupchk";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
			//---------FORWARD 후의 응답
			out.print("AFTER FORWARD");
			
		}else if("redirect".equals(opt)){
			response.sendRedirect("http://www.google.com");
			
		}else if("include".equals(opt)) {
			
			//---------INCLUDE 전의 응답
			PrintWriter out = response.getWriter();
			out.print("BEFORE INCLUDE");
			String path = "/iddupchk";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.include(request, response);
			
			//---------INCLUDE 후의 응답
			out.print("AFTER INCLUDE");
		
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<ul>");

			out.print("<li>");
			out.print("<a href=\"move?opt=forward\">FORWARD</a>");
			out.print("</li>");

			out.print("<li>");
			out.print("<a href=\"move?opt=redirect\">REDIRECT</a>");
			out.print("</li>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=include\">INCLUDE</a>");
			out.print("</li>");
			
			out.print("</ul>");
			//forward -> 서버 차원의 페이지이동, 기존 객체를 forward된 페이지에서도 사용 가능, 같은 웹컨텍스트에서만 이동가능, 웹브라우저url 바뀌지 않음 (백엔드에서 이동)
			//redirect -> 클라이언트 차원의 이동(자동재요청), 기존 객체를 redirect된 페이지에서 사용 불가, 다른 웹컨텍스트로 이동가능, 웹브라우저에서의 url도 바뀌게됨
			//forward가 기본 이동 방
		}
	}
}
