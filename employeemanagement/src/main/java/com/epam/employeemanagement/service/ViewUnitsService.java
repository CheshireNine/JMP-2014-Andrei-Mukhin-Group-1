package com.epam.employeemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.employeemanagement.dao.UnitDAO;
import com.epam.employeemanagement.model.Unit;


public class ViewUnitsService implements ActionService {

    @Autowired
    private UnitDAO unitDAO;

    public ViewUnitsService() {
    }

    public String execute(HttpServletRequest req) {
        List<Unit> units = unitDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_UNIT_LIST_NAME, units);
        return ServiceConstants.UNITS_PAGE_PATH;
    }
}
