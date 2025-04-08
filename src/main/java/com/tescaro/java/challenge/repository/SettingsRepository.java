package com.tescaro.java.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tescaro.java.challenge.model.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Optional<Settings> findByHash(String hash);
}
