package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.employeemanagement.model.EmployeeStatus;

public class AddEmployeeService implements ActionService {

    public AddEmployeeService() {
    }

    public String execute(HttpServletRequest req) {
        List<EmployeeStatus> statusList = new ArrayList<EmployeeStatus>( Arrays.asList(EmployeeStatus.values() ));
        req.setAttribute(ServiceConstants.ATTRIBUTE_STATUS_LIST_NAME, statusList);
        return ServiceConstants.EMPLOYEE_ADD_PAGE_PATH;
    }
}
