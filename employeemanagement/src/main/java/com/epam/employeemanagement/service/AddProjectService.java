package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class AddProjectService implements ActionService {

    public AddProjectService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.PROJECT_ADD_PAGE_PATH;
    }
}
