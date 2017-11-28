package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}