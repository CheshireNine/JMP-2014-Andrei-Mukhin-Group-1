package com.epam.employeemanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.UnitDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.model.EmployeeStatus;
import com.epam.employeemanagement.model.Unit;


public class PerformSelectUnitService implements ActionService {

    @Autowired
    private UnitDAO unitDAO;

    public PerformSelectUnitService() {
    }

    public String execute(HttpServletRequest req) {
        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Employee employee = dataBean.getCurrentEmployee();
        String  id = req.getParameter(ServiceConstants.PARAMETER_ID_NAME);
        if(id != null) {
            long unitId = Long.parseLong(id);
            Unit unit = unitDAO.find(unitId);
            if(unit != null) {
                employee.setUnit(unit);;
            }
        }
        dataBean.setCurrentEmployee(employee);

        List<EmployeeStatus> statusList = new ArrayList<EmployeeStatus>( Arrays.asList(EmployeeStatus.values() ));
        req.setAttribute(ServiceConstants.ATTRIBUTE_STATUS_LIST_NAME, statusList);
        return ServiceConstants.EMPLOYEE_EDIT_PAGE_PATH;
    }
}
