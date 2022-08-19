package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dto.Product;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prod_no");
		String quantity = request.getParameter("quantity");

		HttpSession session = request.getSession();
		//카트 먼저 꺼내와서 카트가 있는지 없는지 먼저 판단 (카트가 없으면 만들고 있으면 그대로 사용)
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");                                                                                     
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}

		Product p = new Product(); p.setProdNo(prodNo);
		int newQuantity = Integer.parseInt(quantity);
		Integer oldQuantity = cart.get(p);
		if(oldQuantity != null) { //상품이 있는 경우
			newQuantity+= oldQuantity;
		}
		cart.put(p, newQuantity);

		System.out.println("장바구니 목록 수 :" + cart.size());
		System.out.println(cart);
//		for(Map <Product, Integer> map: cart) {
//			Integer oldQuantity = map.get(p);  //매개변수로 사용된 변수의 해시코드와 equals 메서드가 오버라이딩 되어있어야함
//			if(oldQuantity != null) { //상품이 없는 경우
//				break;
//			
//		Map<Product, Integer> map = new HashMap<>();  //반복문이 끝나면 새로 map을 만들어서 누적
//		map.put(p, Integer.parseInt(quantity));
//		cart.add(map);

//		boolean exist = false; //상품존재 여부
//		outer : for(Map <Product, Integer> map: cart) {
//			Set<Product> products = map.keySet(); //장바구니 상품들
//			inner: for(Product p: products) { //상품
//				if(p.getProdNo().equals(prodNo)) { //상품번호가 존재하면
//					//수량만 증가, 반복 종료
//					int oldQuantity = map.get(p);
//					map.put(p, oldQuantity+Integer.parseInt(quantity)); //?
//					exist = true;
//					break outer;
//				}	
//			}
//		}
//		if(!exist) {
//			//장바구니에 추가
//			Product p = new Product();
//			p.setProdNo(prodNo);
//			Map<Product, Integer> map = new HashMap<>();
//			map.put(p, Integer.parseInt(quantity));
//			cart.add(map);
//		}
//		System.out.println("장바구니 목록 수 :" + cart.size());
//		System.out.println(cart);

//		이렇게 쓰면 누적의 개념이 적용되지 않음
//		List<Map<Product, Integer>> cart = new ArrayList<>();
//		
//		Map<Product, Integer> map = new HashMap<>();
//		Product p = new Product(); p.setProdNo(prodNo);
//		map.put(p, Integer.parseInt(quantity));
//		cart.add(map); 
//		
//		session.setAttribute("cart", cart);
	}
}

