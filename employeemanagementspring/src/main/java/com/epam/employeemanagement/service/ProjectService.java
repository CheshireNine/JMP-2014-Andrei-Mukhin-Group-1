package com.epam.employeemanagement.service;

import java.util.List;

import com.epam.employeemanagement.model.Project;

public interface ProjectService {
    List<Project> list(int pageNum);
    Project view(Long projectId);
    Project save(Project project);
    void delete(Long projectId);
    boolean exists(Long projectId);
    long count();
    int getPageSize();
}
