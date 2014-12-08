package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class SelectProjectService implements ActionService {

    public SelectProjectService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.PROJECT_SELECT_PAGE_PATH;
    }
}
