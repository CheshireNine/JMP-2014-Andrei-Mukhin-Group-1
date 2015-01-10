package com.epam.employeemanagement.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.employeemanagement.model.Employee;


public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    List<Employee> findAllByLastNameLike(String name, Pageable page);
    long countByLastNameLike(String name);
}
