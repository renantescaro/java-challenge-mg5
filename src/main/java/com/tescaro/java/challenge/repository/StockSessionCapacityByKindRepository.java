package com.tescaro.java.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tescaro.java.challenge.model.StockSessionCapacityByKind;

public interface StockSessionCapacityByKindRepository extends JpaRepository<StockSessionCapacityByKind, Long> {
}
