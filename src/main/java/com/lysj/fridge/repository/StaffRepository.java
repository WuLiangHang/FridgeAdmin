package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	Staff findByUsername(String username);

	Staff findByToken(String token);
}
