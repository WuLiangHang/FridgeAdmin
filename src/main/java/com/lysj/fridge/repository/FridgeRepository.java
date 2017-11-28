package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Fridge;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {

}