package com.learning.app;

import org.springframework.web.bind.annotation.RestController;

import com.learning.app.entities.Product;
import com.learning.app.repos.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepo;
	
	@PostMapping("/prodcut")
	public Product createProduct(@RequestBody Product product) {
		//TODO: process POST request
		return productRepo.save(product);
		
	}
	
	
}
