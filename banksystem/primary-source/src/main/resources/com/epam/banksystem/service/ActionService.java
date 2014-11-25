package com.epam.banksystem.service;

import javax.servlet.http.HttpServletRequest;

public interface ActionService {
    String execute(HttpServletRequest req);
}
