package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Account;

import ccom.example.demo.exception.ModifyException;
@SpringBootTest
class AccountServiceTest {
	@Autowired
	AccountService service;
	@Test
	void testOpen() {
		//계좌신설테스트하기 위해서는 테이블이 필요하다.spring container가 구동될때 테이블자동생성되도록 
		//application.properties파일의 ddl-auto속성값을 create로
		Account a1 = new Account();
		a1.setAccountNo("101");
		a1.setAccountBalance(1000);
		service.open(a1); //tx1
		
		Account a2 = new Account();
		a2.setAccountNo("102");
		a2.setAccountBalance(1000);
		service.open(a2);//tx2
	}
	
	@Test
	void testTransferSuccess() throws ModifyException {
		//spring container가 구동될때 테이블생성안되도록 application.properties파일의 ddl-auto속성값을 update
		String from ="101";
		String to = "102";
		int amount = 10;
		service.transfer(from, to, amount);//0234060946~7
	}
	@Test
	void testTransferFail() throws ModifyException {
		//ModifyExcepiton ⇒ CheckedException
		String from ="101";
		String to = "999";
		int amount = 10;
		service.transfer(from, to, amount);
	}
}
