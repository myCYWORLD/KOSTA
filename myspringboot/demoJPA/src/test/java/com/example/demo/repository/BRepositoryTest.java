package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.B;
import com.example.demo.entity.M;
@SpringBootTest
class BRepositoryTest {
	@Autowired
	private BRepository brepository;

	@Autowired
	private MRepository mrepository;

	@Test
	void testSave() {
		M m = new M();
		m.setId("id1");
		m.setName("나나나");
		m.setRole("바보");
		mrepository.save(m);

		M m1 = new M();
		m1.setId("id2");
		m1.setName("너너너");
		m1.setRole("멍청이");
		mrepository.save(m1);

		for(int i=1; i<=3; i++) {
			B b = new B();
			b.setM(m);
			b.setTitle("나나나가 등록한 게시글" + i);
			brepository.save(b);
		}
		for(int i=1; i<=3; i++) {
			B b = new B();
			b.setM(m1);
			b.setTitle("너너너가 등록한 게시글" + i);
			brepository.save(b);
		}
	}
	
	@Test
	void testSelectById() {
		B b = brepository.findById(1).get();
		System.out.println(b.getM().getName());
	}
	
	
	
}