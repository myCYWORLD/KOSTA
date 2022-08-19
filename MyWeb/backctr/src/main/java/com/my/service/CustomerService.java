package com.my.service;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerOracleRepository;
import com.my.repository.CustomerRepository;

//service layer
//웹에 종속적이지 않아야함 -> 재사용성이 떨어짐(응답과 웹에 관련된 것은 controller에서 처리)

public class CustomerService {
	private CustomerRepository repository;
	public CustomerService() {
		this.repository = new CustomerOracleRepository();
	}
	public Customer login(String id, String pwd) 
			throws FindException {
		
		Customer c = repository.selectById(id);
		if(!c.getPwd().equals(pwd)) {
			throw new FindException();
		}
		return c;
	}
	
	public Customer iddupchk(String id) 
			throws FindException {
		return repository.selectById(id);
	}

	public void signup(Customer c) 
			throws AddException {
		repository.insert(c);
	}
}



