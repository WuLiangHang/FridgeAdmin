package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}