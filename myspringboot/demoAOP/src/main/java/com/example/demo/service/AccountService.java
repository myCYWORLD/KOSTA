package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import ccom.example.demo.exception.ModifyException;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	/**
	 * 계좌를 만든다
	 * @param a
	 */
	public void open(Account a) {
		repository.save(a);  //jpa용 repository  account행이 없으면 만들어지고 있으면 기존 행이 수정됨
	}
	/**
	 * 입금한다
	 * @param no 계좌번호
	 * @param amount 입금액
	 */
	public void deposit(String no, int amount) throws ModifyException{
		Optional<Account> optA = repository.findById(no);    //계좌를 찾는다
//		if(!optA.isPresent()) {
//			new IllegalArgumentException(no + "계좌가 없습니다");
//		} 
//		=> optA.orElseThrow(()->new IllegalArgumentException(no+"계좌가 없습니다"));와 같은 코드
		optA.orElseThrow(()->new ModifyException(no+"계좌가 없습니다"));
//		optA.orElseThrow(()->new IllegalArgumentException(no+"계좌가 없습니다")); //uncheckedexception -> 자동롤백
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		a.setAccountBalance(aBalance + amount );
		repository.save(a);
	}
	/**
	 * 출금한다
	 * @param no 계좌번호
	 * @param amount 출금액
	 * @throws ModifyException 
	 */
	public void withdraw(String no, int amount) throws ModifyException {
		Optional<Account> optA = repository.findById(no);  //find메서드로 manged상태를 만들어놓음
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); //잔액을 불러움
		if(amount > aBalance ) {  //잔액이 출금할 금액보다 작으면 예외처리
			throw new ModifyException("잔액이 부족합니다");
//			throw new IllegalArgumentException("잔액이 부족합니다");
		}
		a.setAccountBalance(aBalance - amount ); //기존 잔액에서 출금액을 뺌
		repository.save(a);  //managed 상태여서 merge가 되어 자료가 없으면 insert가 되고 있으면 update가 됨
							 //springbootjpa는 메서드가 시행되면 자동 트랜잭션이 시행 -> 메서드 정상처리경우 commit();
	}
	/**
	 * 계좌이체한다
	 * @param from 출금계좌번호
	 * @param to 입금계좌번호
	 * @param amount 이체금액
	 * @throws ModifyException
	 */
	@Transactional(rollbackFor = ModifyException.class) //(rollbackFor = ModifyException.class) => ModifyException에서 문제가 발생하면 자동 롤백하라
	public void transfer(String from, String to, int amount) throws ModifyException{
		withdraw(from, amount);  //성공
		deposit(to, amount);	 //실패 
	}
}