package com.tescaro.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tescaro.java.challenge.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {}
