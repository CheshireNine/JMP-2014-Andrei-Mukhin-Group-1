package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.epam.employeemanagement.dao.EmployeeDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Address;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;

public class SaveEmployeeService implements ActionService {

    @Autowired
    EmployeeDAO employeeDAO;

    public SaveEmployeeService() {
    }

    @Transactional
    public String execute(HttpServletRequest req) {
        String firstName = req.getParameter(ServiceConstants.PARAMETER_FIRST_NAME);
        String lastName = req.getParameter(ServiceConstants.PARAMETER_LAST_NAME);
        String status = req.getParameter(ServiceConstants.PARAMETER_STATUS);
        String city = req.getParameter(ServiceConstants.PARAMETER_CITY);
        String street = req.getParameter(ServiceConstants.PARAMETER_STREET);

        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Employee employee = dataBean.getCurrentEmployee();
        boolean isNewEmployee = false;
        if(employee == null) {
            employee = new Employee();
            isNewEmployee = true;
        }
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        if((status != null) && (status.length() > 0)) {
            employee.setStatus(EmployeeStatus.valueOf(status));
        }
        if((city != null) || (street != null)) {
            Address address = (!isNewEmployee && (employee.getAddress() != null)) ?
                    employee.getAddress() : new Address();
            address.setCity(city);
            address.setStreet(street);
            employee.setAddress(address);
        }

        if (isNewEmployee) {
            employeeDAO.create(employee);
        } else {
            employeeDAO.edit(employee);
        }

        
        dataBean.setCurrentEmployee(null);
        List<Employee> accounts = employeeDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_EMPLOYEE_LIST_NAME, accounts);
        return ServiceConstants.EMPLOYEES_PAGE_PATH;
    }
}
