package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;
import com.my.sql.MyConnection;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8"); //charset은 대소문자 구분X
		PrintWriter out = response.getWriter();

		// ------------------------
		ProductRepository repository = new ProductOracleRepository();
		List<Product> products;
		try {
			products = repository.selectAll();
		}catch(FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}


		// ------------------------


		String result ="["; //자바스크립트의 []
		for(int i = 0; i<products.size(); i++) {
			if(i>0) {
				result += ",";
			}
			Product p = products.get(i);

			result += "{"; //자바스크립트 객체{}
			result += "\"prod_no\":"; result += "\""+ p.getProdNo()+"\""; result += ",";
			result += "\"prod_name\":"; result += "\""+ p.getProdName()+"\""; result += ",";
			result += "\"prod_price\":"; result += p.getProdPrice(); // "2000" 대신
			result += "}";
		}
		//      result += "{";
		//      result += "\"prod_no\":"; result += "\"C0002\""; result += ",";
		//      result += "\"prod_name\":"; result += "\"아메리카노\""; result += ",";
		//      result += "\"prod_price\":"; result += "1000";  // 숫자타입은 "\\"넣어주지 않아도 됨
		//      result += "}";
		//      result += ",";
		//      
		//      result += "{";
		//      result += "\"prod_no\":"; result += "\"C00023\""; result += ",";
		//      result += "\"prod_name\":"; result += "\"바닐라플랫화이트\""; result += ",";
		//      result += "\"prod_price\":"; result += "1500";  // 숫자타입은 "\\"넣어주지 않아도 됨
		//      result += "}";

		result += "]";

		out.print(result);
	}

}