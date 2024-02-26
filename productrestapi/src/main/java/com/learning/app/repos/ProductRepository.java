package com.learning.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
