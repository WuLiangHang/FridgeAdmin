package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}