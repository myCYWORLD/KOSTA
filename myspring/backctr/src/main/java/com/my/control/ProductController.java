package com.my.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
//	@GetMapping("viewAll")
//	@ResponseBody
//	public Map<String, Object> viewAll() {
//		Map<String, Object> map = new HashMap<>();
//		Product p = new Product();
//		map.put("product", p);
//		map.put("msg", "상품이 없습니다.");
//		try {
//			
//		}
//	}
	
	
	@GetMapping("viewproduct")
	@ResponseBody
	public Map<String, Object> view(String prod_no) {
		Map<String, Object> map = new HashMap<>();
		Product p = new Product();
		map.put("product", p);
		map.put("status", 0);
		map.put("msg", "상품이 없습니다.");
		try {
			p = service.view(prod_no);
			map.put("product", p);
			map.put("status", 1);
			map.put("msg", "상품을 찾음");
		} catch (FindException e) {
			
			e.printStackTrace();
		}
		return map;
		
	}
	
	@GetMapping("search")
	@ResponseBody
	public Object search(String word) {
		try {
			return service.search(word);
		} catch (FindException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
