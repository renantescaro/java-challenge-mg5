package com.tescaro.java.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 200)
	private String hash;

    @Column(nullable = false, unique = false, length = 400)
	private String value;

    // Constructors
	public Settings() {}

	public Settings(String hash, String value) {
		this.hash = hash;
		this.value = value;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
