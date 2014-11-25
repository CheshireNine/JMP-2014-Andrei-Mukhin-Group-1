package com.epam.banksystem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.epam.banksystem.dao.AccountsDAOLocal;
import com.epam.banksystem.dao.BanksDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Bank;

@Stateless
public class ViewBankService implements ActionService {
    @EJB
    AccountsDAOLocal accounstDAO;
    @EJB
    BanksDAOLocal banksDAO;

    public ViewBankService() {
    }

    public String execute(HttpServletRequest req) {
        long bankId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Bank bank = banksDAO.find(bankId);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentBank(bank);
        List<Account> accounts = accounstDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_ACCOUNT_LIST_NAME, accounts);
        return ServiceConstants.BANK_PAGE_PATH;
    }

}
