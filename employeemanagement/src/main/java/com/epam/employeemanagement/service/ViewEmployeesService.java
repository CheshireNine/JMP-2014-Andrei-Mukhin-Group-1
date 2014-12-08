package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.model.Employee;

public class ViewEmployeesService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public ViewEmployeesService() {
    }

    public String execute(HttpServletRequest req) {
        List<Employee> accounts = employeeDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, accounts);
        return ServiceConstants.EMPLOYEES_PAGE_PATH;
    }
}
