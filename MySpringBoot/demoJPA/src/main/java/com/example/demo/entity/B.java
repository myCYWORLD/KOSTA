package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NoArgsConstructor
@Setter @Getter
public class B {
	@Id @GeneratedValue
	private int seq;

	private String title;
	
	@ManyToOne
	@JoinColumn(name="m_id")
	private M m;
	
}
