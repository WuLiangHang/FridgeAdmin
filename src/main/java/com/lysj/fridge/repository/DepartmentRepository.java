package com.lysj.fridge.repository;

import com.lysj.fridge.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(long departmentId);

    Department findByName(String name);


}