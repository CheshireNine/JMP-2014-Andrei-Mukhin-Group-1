package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.ProjectDAO;
import com.epam.employeemanagement.model.Project;


public class ViewProjectsService implements ActionService {

    @Autowired
    private ProjectDAO projectDAO;

    public ViewProjectsService() {
    }

    public String execute(HttpServletRequest req) {
        List<Project> projects = projectDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_PROJECT_LIST_NAME, projects);
        return ServiceConstants.PROJECTS_PAGE_PATH;
    }
}
