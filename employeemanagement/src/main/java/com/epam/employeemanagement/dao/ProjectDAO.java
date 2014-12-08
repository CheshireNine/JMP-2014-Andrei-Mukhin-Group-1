package com.epam.employeemanagement.dao;

import java.util.List;

import com.epam.employeemanagement.model.Project;


public interface ProjectDAO {
    Project create(Project project);
    Project edit(Project project);
    void remove(Project project);
    Project find(Object id);
    List<Project> findAll();
}
