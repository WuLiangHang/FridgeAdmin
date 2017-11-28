package com.lysj.fridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysj.fridge.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}