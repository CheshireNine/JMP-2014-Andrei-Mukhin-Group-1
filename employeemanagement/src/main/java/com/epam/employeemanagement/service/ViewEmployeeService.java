package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Employee;

public class ViewEmployeeService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public ViewEmployeeService() {
    }

    public String execute(HttpServletRequest req) {
        long employeeId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Employee employee = employeeDAO.find(employeeId);
        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentEmployee(employee);
        return ServiceConstants.EMPLOYEE_VIEW_PAGE_PATH;
    }
}
