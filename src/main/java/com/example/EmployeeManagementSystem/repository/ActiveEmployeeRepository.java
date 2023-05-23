package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.model.ActiveEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveEmployeeRepository extends JpaRepository<ActiveEmployee,Long> {
}
