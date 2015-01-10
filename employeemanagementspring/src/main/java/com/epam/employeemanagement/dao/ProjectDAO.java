package com.epam.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.employeemanagement.model.Project;


public interface ProjectDAO extends JpaRepository<Project, Long> {
}
