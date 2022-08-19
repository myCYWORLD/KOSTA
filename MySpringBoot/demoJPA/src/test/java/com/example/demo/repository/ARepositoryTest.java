package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.A;
@SpringBootTest
class ARepositoryTest {
	@Autowired
	private ARepository repository;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	
	@Test
//	@Transactional //롤백을 시키고 싶을 때 사용하는 어노테이션
	void testSave() {
		A a = new A();
		a.setA1("1"); 
		a.setA2(new BigDecimal(1.0));
		a.setA4("a4_1");
		repository.save(a);
		//Hibernate: select a0_.a1 as a1_0_0_, a0_.a2_c as a2_0_0_, a0_.a3 as a3_0_0_, a0_.a4_c as a4_0_0_ from a_tbl a0_ where a0_.a1=?
		//JPA의 find()메서드 호출 
		//같은 컬럼이 있나? 
		
		//Hibernate: insert into a_tbl (a2_c, a3, a4_c, a1) values (?, ?, ?, ?)
		//JPA의 persist()메서드 호출
		//없으니 삽입
		
		A aa = new A();
		aa.setA1("1");
		aa.setA2(new BigDecimal(2));
		aa.setA4("a4_2");
		repository.save(aa);
		//Hibernate: select a0_.a1 as a1_0_0_, a0_.a2_c as a2_0_0_, a0_.a3 as a3_0_0_, a0_.a4_c as a4_0_0_ from a_tbl a0_ where a0_.a1=?
		//JPA의 find()메서드 호출
		//같은 컬럼이 있나?
		
		//Hibernate: update a_tbl set a2_c=?, a3=?, a4_c=? where a1=?
		//JPA의 set()메서드 호출 
		//이미 있으니 업데이트
		
		A aaa = new A();
		aaa.setA1("3");
		aaa.setA2(new BigDecimal(3));
		aaa.setA4("a4_3");
		repository.save(aaa);
	}
	@Test
	void testFindById() {
		Optional <A> optA = repository.findById("1"); //JPA의 find()메서드 호출 
		assertTrue(optA.isPresent());
		String expectedA4 = "a4_2";
		A a = optA.get();
		assertEquals(expectedA4, a.getA4());
	}
	
	@Test
	void testDeleteById() {
		repository.deleteById("1");
		//JPA의 remove() 메서드 호출
		//Hibernate: select a0_.a1_c as a1_0_0_, a0_.a2_c as a2_0_0_, a0_.a3 as a3_0_0_, a0_.a4_c as a4_0_0_ from a_tbl a0_ where a0_.a1_c=?
		//Hibernate: delete from a_tbl where a1_c=?
		Optional<A> optA = repository.findById("1");
		//
		assertFalse(optA.isPresent());
	}
	
	@Test
	void testFindAll() {
		Iterable<A> list = repository.findAll();
		logger.error(list.toString());
	}
	
	@Test
	void testFindByA4() {
		List<A> list = repository.findByA4("a4_3"); //Hibernate: select a0_.a1_c as a1_0_, a0_.a2_c as a2_0_, a0_.a3 as a3_0_, a0_.a4_c as a4_0_ from a_tbl a0_ where a0_.a4_c=?
		logger.error(list.toString());
	}
	
	@Test
	void testCount() {
		long cnt = repository.count();
		}
	
	@Test
	void testFindByA4Like() {
		List<A> list = repository.findByA4Like("%a%");
		logger.error(list.toString());
	}

}
