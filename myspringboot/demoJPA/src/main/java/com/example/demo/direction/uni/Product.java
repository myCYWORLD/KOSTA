package com.example.demo.direction.uni;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name="product_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Product {
	
	@Id
	private String prodNo; // 식별자 역할
	private String prodName;
	private int prodPrice;
	// 테이블 생성 시 DB에 _ 패턴으로 저장되어있음 (자동 매핑)
}
