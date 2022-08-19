package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductRepository;

@Service(value= "productService")
public class ProductService {
	@Autowired
	private ProductRepository repository;

	public void list() throws FindException {
		repository.selectAll();
	}

	public ProductRepository getRepository() {
		return repository;
	}

	public Product view(String prod_no) throws FindException {
		return repository.selectByProdNo(prod_no);
	}

	public List<Product> search(String word) throws FindException {
		return repository.selectByProdNoOrProdName(word);
	}	
}

