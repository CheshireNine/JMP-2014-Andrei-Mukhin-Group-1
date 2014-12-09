package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.epam.employeemanagement.dao.ProjectDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Project;

public class SaveProjectService implements ActionService {

    @Autowired
    ProjectDAO projectDAO;

    public SaveProjectService() {
    }

    @Transactional
    public String execute(HttpServletRequest req) {
        String name = req.getParameter(ServiceConstants.PARAMETER_NAME);


        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Project project = dataBean.getCurrentProject();
        boolean isNewProject = false;
        if(project == null) {
            project = new Project();
            isNewProject = true;
        }
        project.setName(name);

        if (isNewProject) {
            projectDAO.create(project);
        } else {
            projectDAO.edit(project);
        }

        
        dataBean.setCurrentEmployee(null);
        List<Project> projects = projectDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_PROJECT_LIST_NAME, projects);
        return ServiceConstants.PROJECTS_PAGE_PATH;
    }
}
