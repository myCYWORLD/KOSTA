package com.example.demo.direction.bi;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode // 두 복합기를 결합하여 해시코드 만들고, 두 복합키 결합하여 같은가 만드는 어노테이션
public class OrderLinePK implements Serializable{ // 시리얼라이즈 인터페이스를 구현한 하위 클래스
	private static final long serialVersionUID = 1L ;// 객체 직렬화한 객체 구분해주는 것
//	private Long orderNo;
	private OrderInfo orderInfo;
	private Product orderP;
	
	
	
}
