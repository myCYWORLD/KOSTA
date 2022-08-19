package com.example.demodocker1.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demodocker1.entity.Test;
import com.example.demodocker1.repository.TestRepository;

@RestController
public class WelcomeController {
	@Autowired
	private TestRepository repository;
	
	@GetMapping("/")
	public String welcome() {
		return "WELCOME";
	}
	@GetMapping("/list")
	public List<Test> list() {
		return repository.findAll();		
	}
}
