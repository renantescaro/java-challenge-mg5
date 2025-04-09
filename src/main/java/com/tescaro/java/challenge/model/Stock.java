package com.tescaro.java.challenge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.tescaro.java.challenge.enums.StockKindEnum;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stock_session_id", nullable = false)
    private StockSession stockSession;

    @Column(name = "last_movement", nullable = false)
    private LocalDateTime lastMovement;

    @Column(nullable = false)
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StockKindEnum kind;

    // Constructors
    public Stock() {
        this.lastMovement = LocalDateTime.now();
    }

    public Stock(
            Product product,
            StockSession stockSession,
            StockKindEnum kind) {
        this.product = product;
        this.stockSession = stockSession;
        this.kind = kind;
        this.lastMovement = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StockSession getStockSession() {
        return stockSession;
    }

    public void setStockSession(StockSession stockSession) {
        this.stockSession = stockSession;
    }

    public LocalDateTime getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(LocalDateTime lastMovement) {
        this.lastMovement = lastMovement;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public StockKindEnum getKind() {
        return kind;
    }

    public void setKind(StockKindEnum kind) {
        this.kind = kind;
    }
}
