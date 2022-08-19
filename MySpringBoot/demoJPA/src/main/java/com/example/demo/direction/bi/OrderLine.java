package com.example.demo.direction.bi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_line_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@IdClass(OrderLinePK.class) //OrderLinePK가 PK를 담당하는 Class라는 것을 알려줘야함
public class OrderLine { // OrderLine class 복합키로 처리된다.
	@Id
//	@Column(name="order_line_no") // id로 사용될 컬럼명 order_line_no
	@ManyToOne  
	@JoinColumn(name = "order_line_no") // Many의 입장에서 Oner을 가리킬 때 FK로 참여할 컬럼의 이름이 order_line_no
	private OrderInfo orderInfo; // 양방향 하기 위해 orderInfo 타입의 orderInfo를 pk로 일단 바꿈
//	private Long orderNo;
		
	@Id
	@ManyToOne
	@JoinColumn(name = "order_prod_no", nullable = false)
	private Product orderP;
	
	private int orderQuantity;
}