package com.epam.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.employeemanagement.model.Unit;

public interface UnitDAO extends JpaRepository<Unit, Long> {
}
