package com.tescaro.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tescaro.java.challenge.model.StockSession;

public interface StockSessionRepository extends JpaRepository<StockSession, Long> {}
