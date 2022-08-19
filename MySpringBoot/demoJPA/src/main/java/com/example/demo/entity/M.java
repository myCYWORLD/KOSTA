package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NoArgsConstructor
@Setter @Getter
public class M {
	@Id
	private String id;
	private String name;
	private String role;
}
