package com.tescaro.java.challenge.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_kinds")
public class ProductKind {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String name;

	@Column(name = "separate_stock", nullable = false)
	private Boolean separateStock;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	// Constructors
	public ProductKind() {
		this.createdAt = LocalDateTime.now();
	}

	public ProductKind(String name, Boolean separateStock, String email) {
		this.name = name;
		this.separateStock = separateStock;
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

	public Boolean getSeparateStock() {
		return separateStock;
	}

	public void setSeparateStock(Boolean separateStock) {
		this.separateStock = separateStock;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
