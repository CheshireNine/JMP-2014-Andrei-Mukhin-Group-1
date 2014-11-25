package com.epam.banksystem.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.banksystem.dao.AccountsDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Account;
import com.epam.banksystem.requesthandler.RequestHandler;

@Stateless
public class AddAccountService implements ActionService {
    private static final Logger LOG = Logger.getLogger(RequestHandler.class);
    @EJB
    AccountsDAOLocal accounstDAO;

    public AddAccountService() {
    }

    public String execute(HttpServletRequest req) {
        return ServiceConstants.ACCOUNT_ADD_PAGE_PATH;
    }

}
