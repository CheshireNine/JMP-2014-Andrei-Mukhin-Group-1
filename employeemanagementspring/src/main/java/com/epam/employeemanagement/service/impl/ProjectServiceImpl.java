package com.epam.employeemanagement.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.employeemanagement.dao.ProjectDAO;
import com.epam.employeemanagement.model.Project;
import com.epam.employeemanagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Value("${page.defaultorder}")
    private String direction;

    @Value("${page.size}")
    private int pageSize;

    private Sort sort;

    public ProjectServiceImpl() {
    }

    @PostConstruct
    public void initEmployeeSort() {
        sort = new Sort(Sort.Direction.valueOf(direction), "name");
    }

    public List<Project> list(int pageNum) {
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        Page<Project> projectPage = projectDAO.findAll(pageable);
        return (List<Project>)projectPage.getContent();
    }

    public Project view(Long projectId) {
        return projectDAO.findOne(projectId);
    }

    public Project save(Project project) {
        return projectDAO.saveAndFlush(project);
    }

    @Override
    public void delete(Long projectId) {
        projectDAO.delete(projectId);
        projectDAO.flush();
    }

    @Override
    public boolean exists(Long projectId) {
        return projectDAO.exists(projectId);
    }

    @Override
    public long count() {
        return projectDAO.count();
    }

    public int getPageSize() {
        return pageSize;
    }
}
