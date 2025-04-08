package com.tescaro.java.challenge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_session_capacity_by_kind")
public class StockSessionCapacityByKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_kind_id", nullable = false, unique = true)
    private ProductKind productKind;

    @Column(name = "capacity_liters", nullable = false)
    private Long capacityLiters;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public StockSessionCapacityByKind() {
        this.createdAt = LocalDateTime.now();
    }

    public StockSessionCapacityByKind(
        ProductKind productKind,
        Long capacityLiters) {
        this.productKind = productKind;
        this.capacityLiters = capacityLiters;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductKind getProductKind() {
        return productKind;
    }

    public void setProductKind(ProductKind productKind) {
        this.productKind = productKind;
    }

    public Long getCapacityLiters() {
        return capacityLiters;
    }

    public void setCapacityLiters(Long capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
