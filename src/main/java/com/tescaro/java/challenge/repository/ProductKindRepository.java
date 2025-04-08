package com.tescaro.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tescaro.java.challenge.model.ProductKind;

public interface ProductKindRepository extends JpaRepository<ProductKind, Long> {}
