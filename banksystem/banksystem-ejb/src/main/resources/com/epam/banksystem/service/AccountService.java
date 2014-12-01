package com.epam.banksystem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.epam.banksystem.dao.AccountsDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Currency;
import com.epam.banksystem.requesthandler.HandlerConstants;

@Stateless
public class AccountService implements ActionService {
    @EJB
    AccountsDAOLocal accountsDAO;

    public AccountService() {
    }

    public String execute(HttpServletRequest req) {
        String actionName = req.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        String page = null;
        if(HandlerConstants.PARAMETER_ACTION_VIEW_ALL.equals(actionName)) {
            page = prepareAccountsPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_VIEW.equals(actionName)) {
            page = prepareAccountViewPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_ADD.equals(actionName)) {
            page = prepareAddAccountPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_EDIT.equals(actionName)) {
            page = prepareAccountEditPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_SAVE.equals(actionName)) {
            page = performSaveAccount(req);
        } else if(HandlerConstants.PARAMETER_ACTION_PERFORM_EXCHANGE.equals(actionName)) {
            page = performExchangeCurrency(req);
        } else if(HandlerConstants.PARAMETER_ACTION_DELETE.equals(actionName)) {
            page = prepareAccountDeletePage(req);
        }

        return page;
    }

    private String prepareAddAccountPage(HttpServletRequest req) {
        return ServiceConstants.ACCOUNT_ADD_PAGE_PATH;
    }

    public String prepareAccountViewPage(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Account account = accountsDAO.find(accountId);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentAccount(account);
        return ServiceConstants.ACCOUNT_VIEW_PAGE_PATH;
    }
    
    public String prepareAccountEditPage(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Account account = accountsDAO.find(accountId);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentAccount(account);
        return ServiceConstants.ACCOUNT_EDIT_PAGE_PATH;
    }

    public String prepareAccountDeletePage(HttpServletRequest req) {
        long accountId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Account account = accountsDAO.find(accountId);
        accountsDAO.remove(account);
        List<Account> accounts = accountsDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_ACCOUNT_LIST_NAME, accounts);
        return ServiceConstants.ACCOUNTS_PAGE_PATH;
    }

    public String prepareAccountsPage(HttpServletRequest req) {
        List<Account> accounts = accountsDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_ACCOUNT_LIST_NAME, accounts);
        return ServiceConstants.ACCOUNTS_PAGE_PATH;
    }

    private String performSaveAccount(HttpServletRequest req) {
        long amount = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_AMOUNT_NAME));

        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Account account = dataBean.getCurrentAccount();
        boolean isNewAccount = false;
        if(account == null) {
            account = new Account();
            isNewAccount = true;
        }
        account.setAmount(amount);

        if (isNewAccount) {
            accountsDAO.create(account);
        } else {
            accountsDAO.edit(account);
        }

        
        dataBean.setCurrentAccount(null);
        List<Account> accounts = accountsDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_ACCOUNT_LIST_NAME, accounts);

        return ServiceConstants.ACCOUNTS_PAGE_PATH;
    }
    
    private String performExchangeCurrency(HttpServletRequest req) {

        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Account currentAccount = dataBean.getCurrentAccount();
        
        Currency selectedCurrency = dataBean.getTargetCurrency();
        long oldAmount = currentAccount.getAmount();
        float oldRate = currentAccount.getCurrency().getRate();
        int oldPrecision = 10^currentAccount.getCurrency().getPrecision();
        float newRate = selectedCurrency.getRate();
        int newPrecision = 10^selectedCurrency.getPrecision();
        long amount = Math.round((((oldAmount * oldRate) / oldPrecision) / newRate) * newPrecision);
        currentAccount.setAmount(amount);
        currentAccount.setCurrency(selectedCurrency);

        accountsDAO.edit(currentAccount);
        dataBean.setCurrentAccount(null);
        dataBean.setTargetCurrency(null);

        List<Account> accounts = accountsDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_ACCOUNT_LIST_NAME, accounts);

        return ServiceConstants.ACCOUNTS_PAGE_PATH;
    }

}
