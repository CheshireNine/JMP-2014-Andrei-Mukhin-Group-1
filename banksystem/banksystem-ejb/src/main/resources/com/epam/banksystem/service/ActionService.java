package com.epam.banksystem.service;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface ActionService {
    String execute(HttpServletRequest req);
}
