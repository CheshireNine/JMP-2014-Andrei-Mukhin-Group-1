package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class FindEmployeeService implements ActionService {

    public FindEmployeeService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.EMPLOYEE_FIND_PAGE_PATH;
    }
}
