package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;

public class EditEmployeeService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public EditEmployeeService() {
    }

    public String execute(HttpServletRequest req) {
        long employeeId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Employee employee = employeeDAO.find(employeeId);
        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentEmployee(employee);
        List<EmployeeStatus> statusList = new ArrayList<EmployeeStatus>( Arrays.asList(EmployeeStatus.values() ));
        req.setAttribute(ServiceConstants.ATTRIBUTE_STATUS_LIST_NAME, statusList);
        return ServiceConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }
}
