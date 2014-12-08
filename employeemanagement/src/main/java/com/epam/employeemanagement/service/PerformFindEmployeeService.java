package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.model.Employee;

public class PerformFindEmployeeService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public PerformFindEmployeeService() {
    }

    public String execute(HttpServletRequest req) {
        long employeeId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Employee employee = employeeDAO.find(employeeId);

        if(employee != null) {
            List<Employee> accounts = new ArrayList<Employee>();
            accounts.add(employee);
            req.setAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, accounts);
        }

        return ServiceConstants.EMPLOYEE_FIND_RESULT_PAGE_PATH;
    }
}
