package com.tescaro.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tescaro.java.challenge.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
