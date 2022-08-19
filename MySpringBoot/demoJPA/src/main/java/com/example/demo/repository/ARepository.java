package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.A;

public interface ARepository extends CrudRepository<A, String> {
	/**
	 쿼리메서드
	 */
	List<A>findByA4(String a4); //findBy queryBy countBy
	
	List<A>findByA4Like(String word); //findBy queryBy countBy
	
}