package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.OrderInfo;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

import oracle.jdbc.driver.T4CTTIkpdnrdeq;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		HttpSession session = request.getSession();
		
		ObjectMapper mapper = new ObjectMapper(); 
		String loginedId  = mapper.writeValueAsString(session.getAttribute("loginInfo"));

		if(loginedId == null) {
			result = mapper.writeValueAsString(loginedId);
		}else {
			OrderRepository repository = new OrderOracleRepository();
			try {
				List<OrderInfo> infos = repository.selectById(loginedId);
				result = mapper.writeValueAsString(infos);
				System.out.println(infos);
			} catch (FindException e) {
				e.printStackTrace();
				Map map = new HashMap<>();
				map.put("status", -1);
				map.put("msg", e.getMessage());
				result = mapper.writeValueAsString(map);
			}
		}
		out.print(result);
	}
}