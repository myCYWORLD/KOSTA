package com.example.demodocker1.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WelcomeController {
	@GetMapping("/")
	public String welcome() {
		return "WELCOME";
	}
}
