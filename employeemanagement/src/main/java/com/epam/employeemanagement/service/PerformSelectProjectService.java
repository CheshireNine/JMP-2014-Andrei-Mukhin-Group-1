package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.ProjectDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;
import com.epam.employeemanagement.model.Project;


public class PerformSelectProjectService implements ActionService {

    @Autowired
    private ProjectDAO projectDAO;

    public PerformSelectProjectService() {
    }

    public String execute(HttpServletRequest req) {
        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Employee employee = dataBean.getCurrentEmployee();
        String  id = req.getParameter(ServiceConstants.PARAMETER_ID_NAME);
        if(id != null) {
            long projectId = Long.parseLong(id);
            Project project = projectDAO.find(projectId);
            if(project != null) {
                List<Project> projects = employee.getProjects();
                if(projects == null) {
                    projects = new ArrayList<Project>();
                }
                projects.add(project);
                employee.setProjects(projects);
            }
        }
        dataBean.setCurrentEmployee(employee);

        List<EmployeeStatus> statusList = new ArrayList<EmployeeStatus>( Arrays.asList(EmployeeStatus.values() ));
        req.setAttribute(ServiceConstants.ATTRIBUTE_STATUS_LIST_NAME, statusList);
        return ServiceConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }
}
