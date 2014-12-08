package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;
import com.epam.employeemanagement.model.Personal;


public class PerformEditPersonalService implements ActionService {

    public PerformEditPersonalService() {
    }

    public String execute(HttpServletRequest req) {
        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Employee employee = dataBean.getCurrentEmployee();
        String  phoneNumber = req.getParameter(ServiceConstants.PARAMETER_PHONE_NUMBER);
        String  email = req.getParameter(ServiceConstants.PARAMETER_EMAIL);
        String  education = req.getParameter(ServiceConstants.PARAMETER_EDUCATION);

        Personal personal = employee.getPersonalInfo();
        if(personal == null) {
            personal = new Personal();
        }
        personal.setPhoneNumber(phoneNumber);
        personal.setEmail(email);
        personal.setEducation(education);
        personal.setEmployee(employee);
        employee.setPersonalInfo(personal);
        dataBean.setCurrentEmployee(employee);

        List<EmployeeStatus> statusList = new ArrayList<EmployeeStatus>( Arrays.asList(EmployeeStatus.values() ));
        req.setAttribute(ServiceConstants.ATTRIBUTE_STATUS_LIST_NAME, statusList);
        return ServiceConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }
}
