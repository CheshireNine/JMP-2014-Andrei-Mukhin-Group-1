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
public class EditAccountService implements ActionService {
    private static final Logger LOG = Logger.getLogger(RequestHandler.class);
    @EJB
    AccountsDAOLocal accounstDAO;

    public EditAccountService() {
    }

    public String execute(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Account account = accounstDAO.find(accountId);
        //UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        //dataBean.setCurrentAccount(account);

        return ServiceConstants.ACCOUNT_EDIT_PAGE_PATH;
    }

}
