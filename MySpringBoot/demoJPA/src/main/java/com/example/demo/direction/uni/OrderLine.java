package com.example.demo.direction.uni;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "order_line_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class OrderLine { 
	@Id
	@Column(name="order_line_no")
	private Long orderNo; // 단순PK
	
	@ManyToOne // JPA가 알아서 Join할 수 있도록 Many의 입장에서 One이 누군지 지정
	// Many입장에서 HAS A 관계로 ONE을 가지고 있음
	// 주문의 입장에서 상품 정보를 찾음
	// Line이 Product를 has a 관계로 가지고 있음 단방향
	@JoinColumn(name = "order_prod_no" , nullable = false) // 테이블의 컬럼에 order_prod_no가 만들어짐
	// JoinColumn => Join에 참여 할 컬럼을 적어주는 것 (자식의 FK)
	// orderP와 연결된 한 행 (order_prod_no)에는 절대 null값이 올 수 없다
	// outer join쓰지 않게 하기 위해 nullable 줌
	// left outer join로 product와 연결을 할 이유가 없음 - 오버헤드?
	private Product orderP; // has a 관계
	private int orderQuantity;
	
}
