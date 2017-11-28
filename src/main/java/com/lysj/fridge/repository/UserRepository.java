package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByToken(String token);
}
