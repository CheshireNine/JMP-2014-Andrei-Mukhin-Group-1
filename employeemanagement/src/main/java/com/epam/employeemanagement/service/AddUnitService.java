package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class AddUnitService implements ActionService {

    public AddUnitService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.UNIT_ADD_PAGE_PATH;
    }
}
