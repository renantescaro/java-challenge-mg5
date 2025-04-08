package com.tescaro.java.challenge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_kind_id", nullable = false)
    private ProductKind productKind;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long volumeLiters;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public Product() {
        this.createdAt = LocalDateTime.now();
    }

    public Product(
            String name,
            ProductKind productKind,
            Long price,
            Long volumeLiters) {
        this.name = name;
        this.productKind = productKind;
        this.price = price;
        this.volumeLiters = volumeLiters;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductKind getProductKind() {
        return productKind;
    }

    public void setProductKind(ProductKind productKind) {
        this.productKind = productKind;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getVolumeLiters() {
        return volumeLiters;
    }

    public void setVolumeLiters(Long volumeLiters) {
        this.volumeLiters = volumeLiters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
