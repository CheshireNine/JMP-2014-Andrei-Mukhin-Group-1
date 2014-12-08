package com.epam.employeemanagement.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.employeemanagement.form.UserData;

public class InitUserDataService implements ActionService {

    public InitUserDataService() {
    }

    public String execute(HttpServletRequest req) {
        UserData dataBean = new UserData();
        HttpSession session = req.getSession(true);
        session.setAttribute(ServiceConstants.BEAN_USER_DATA_NAME, dataBean);
        return ServiceConstants.MAIN_PAGE_PATH;
    }
}
