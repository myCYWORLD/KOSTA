package com.example.demo.direction.uni.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.direction.uni.OrderInfo;
import com.example.demo.direction.uni.OrderLine;
import com.example.demo.direction.uni.Product;
import com.example.demo.direction.uni.repository.OrderInfoRepository;
@SpringBootTest
class OrderInfoRepositoryTest {
	@Autowired
	private OrderInfoRepository repository;
	
	@Test
	void testInsert() { //info insert를 하면서 line도 insert 되는지 확인
		OrderInfo info = new OrderInfo();
		info.setOrderNo(3L); // 주문번호 3번 설정
		info.setOrderId("id1"); // 주문자 아이디 id1
		info.setOrderDt(new Date());
		List<OrderLine> lines = new ArrayList<>();  //orderLine타입의 list를 만들어 놓고
		
			Product p = new Product();
			p.setProdNo("C0001");
			OrderLine line = new OrderLine(); 
			line.setOrderNo(info.getOrderNo()); //3번. 상세 주문번호를 info의 주문 번호로 설정  = 3번
			line.setOrderQuantity(1);
			line.setOrderP(p);
			lines.add(line); //lines에 상세 정보를 추가함
			
		info.setLines(lines);
		repository.save(info); // 여기서의 repository는 info의 repository 
		// info 객체가 save메서드의 인자로 쓰이면서
		// cascade 설정 해 주었기 때문에 
		// info가 new상태에서 managed 상태가 된다 -> info와 관계가 있는 line들도 new상태에서 managed상태가 됨
	}

	@Test
	void testFindById() {
		Optional<OrderInfo> optInfo = repository.findById(3L);
		assertTrue(optInfo.isPresent());
		OrderInfo info = optInfo.get();
		String id = info.getOrderId();
		// ▲여기까지만 정보 얻어오려면 LAZY 사용해도 OK
		
		List<OrderLine> lines = info.getLines(); //EAGER로 설정 해 주어야 함 
		// lines를 가지고 오면서 info도 가지고 와야 여기에서 데이터 가지고 올 수 있음
		// line 정보를 가지고 오지 않으면 info 정보를 가지고 올 수 없음
		
		// 이어지는 정보까지 사용을 하고 싶을 땐 EAGER
		OrderLine line = lines.get(0);
		Product p = line.getOrderP();
		int quantity = line.getOrderQuantity();
		assertEquals("id1",id); // 3번 주문자의 주문 id가 id1로 예상
		assertEquals("C1", p.getProdName());
		assertEquals(1, quantity);
		
	}
}
