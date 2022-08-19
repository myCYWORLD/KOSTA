package com.example.demo.direction.uni.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.direction.uni.OrderLine;
import com.example.demo.direction.uni.Product;
import com.example.demo.direction.uni.repository.OrderLineRepository;
@SpringBootTest
class OrderLineRepositoryTest {
	@Autowired
	private OrderLineRepository repository;
	
	@Test
	void testInsert() {
		Long orderNo = 3L;
		String prodNo = "C0002";
		int orderQuantity = 3;
		OrderLine line = new OrderLine();
		line.setOrderNo(orderNo);
		
		Product p =  new Product();
		p.setProdNo(prodNo);
		line.setOrderP(p);
		
		line.setOrderQuantity(orderQuantity);
		repository.save(line);
	}
	
	@Test
	void testFindById() {
		Long orderNo = 1L;
		Optional<OrderLine> optLine = repository.findById(orderNo);
		assertTrue(optLine.isPresent()); // optLine 존재하면
		OrderLine line = optLine.get(); // optLine의 내용을 꺼내와서 OrderLine 타입의 line에 넣음
		Product p = line.getOrderP(); // 꺼내온 내용을 p에 넣어줌?!
		
		String expectedProdName="C1";
		int expectedProdPrice = 1000;
		assertEquals(expectedProdName, p.getProdName());
		assertEquals(expectedProdPrice, p.getProdPrice());
	}
}
