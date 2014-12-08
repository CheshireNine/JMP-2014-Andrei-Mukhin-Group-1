package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class SelectUnitService implements ActionService {

    public SelectUnitService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.UNIT_SELECT_PAGE_PATH;
    }
}
