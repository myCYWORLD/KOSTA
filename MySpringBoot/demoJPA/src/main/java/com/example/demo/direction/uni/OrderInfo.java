package com.example.demo.direction.uni;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "order_info_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class OrderInfo {
	@Id
	@Column(name = "order_info_no")
	private Long orderNo;
	private Date orderDt;
	private String orderId;
	//Info가 Lines들을 has a 관계로 가짐
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Info쪽이 Line을 가짐 (line들 여러개를 가짐)
//	@ManyToOne //list 자료형을 ManyToOne과 연결할 수 없다고 에러가 나옴 
	@JoinColumn(name = "order_line_no") // Join에 참여할 컬럼이 무엇인지 결정 (FK)
	// 부모의 Pk는 @Id 로 이미 결정 되어있음
	// 자식의 FK역할을 담당 해 주는 컬럼이 무엇인가 결정하는 것
	private List<OrderLine>lines;
	// ▲ 부모의 입장에서 자식들 갖겠다.

	// 부모가 OrderInfo고 자식이 OrderLine임
	// 자식의 FK는 order_line_no 라는 것을 
}

	// @JoinColumn이 없으면 order_info_jpa_lines 테이블이 만들어짐
	// Info와 Line간의 연결이 없기 때문에 중간 매개체로 JPA가 테이블 하나 자동으로 만들어냄
	// @JoinColumn을 붙여주면 orderInfo와 orderLine이 직접 연결됨