package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.model.Employee;

public class DeleteEmployeeService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public DeleteEmployeeService() {
    }

    @Transactional
    public String execute(HttpServletRequest req) {
        long emloyeeId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Employee employee = employeeDAO.find(emloyeeId);
        employeeDAO.remove(employee);

        List<Employee> accounts = employeeDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, accounts);
        return ServiceConstants.EMPLOYEES_PAGE_PATH;
    }
}
