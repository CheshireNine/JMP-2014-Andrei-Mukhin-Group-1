package com.epam.employeemanagement.service;

import javax.servlet.http.HttpServletRequest;

public class EditPersonalService implements ActionService {

    public EditPersonalService() {
    }

    public String execute(HttpServletRequest req) {

        return ServiceConstants.PERSONAL_EDIT_PAGE_PATH;
    }
}
