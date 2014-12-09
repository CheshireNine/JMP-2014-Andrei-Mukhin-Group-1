package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.epam.employeemanagement.dao.UnitDAO;
import com.epam.employeemanagement.form.UserData;
import com.epam.employeemanagement.model.Unit;

public class SaveUnitService implements ActionService {

    @Autowired
    UnitDAO unitDAO;

    public SaveUnitService() {
    }

    @Transactional
    public String execute(HttpServletRequest req) {
        String name = req.getParameter(ServiceConstants.PARAMETER_NAME);


        UserData dataBean = (UserData)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Unit unit = dataBean.getCurrentUnit();
        boolean isNewUnit = false;
        if(unit == null) {
            unit = new Unit();
            isNewUnit = true;
        }
        unit.setName(name);

        if (isNewUnit) {
            unitDAO.create(unit);
        } else {
            unitDAO.edit(unit);
        }
        
        dataBean.setCurrentEmployee(null);
        List<Unit> units = unitDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_UNIT_LIST_NAME, units);
        return ServiceConstants.UNITS_PAGE_PATH;
    }
}
