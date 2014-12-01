package com.epam.banksystem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.epam.banksystem.dao.CurrencyDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Currency;
import com.epam.banksystem.requesthandler.HandlerConstants;

@Stateless
public class CurrencyService implements ActionService {
    @EJB
    CurrencyDAOLocal currencyDAO;

    public CurrencyService() {
    }

    public String execute(HttpServletRequest req) {
        String actionName = req.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        String page = null;
        if(HandlerConstants.PARAMETER_ACTION_VIEW_ALL.equals(actionName)) {
            page = prepareCyrrencyViewAllPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_ADD.equals(actionName)) {
            page = prepareAddCurrencyPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_SELECT.equals(actionName)) {
            page = prepareSelectCurrencyPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_PERFORM_SELECT.equals(actionName)) {
            page = performCurrencySelect(req);
        } else if(HandlerConstants.PARAMETER_ACTION_PERFORM_SELECT_TARGET.equals(actionName)) {
            page = performTargetCurrencySelect(req);
        } else if(HandlerConstants.PARAMETER_ACTION_FETCH.equals(actionName)) {
            page = fetchByBankId(req);
        } else if(HandlerConstants.PARAMETER_ACTION_SAVE.equals(actionName)) {
            page = performSaveCurrency(req);
        }

        return page;
    }

    private String prepareAddCurrencyPage(HttpServletRequest req) {
        return ServiceConstants.CURRENCY_ADD_PAGE_PATH;
    }

    public String prepareCyrrencyViewAllPage(HttpServletRequest req) {
        List<Currency> currencies = currencyDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_CURRENCY_LIST_NAME, currencies);
        return ServiceConstants.CURRENCIES_PAGE_PATH;
    }

    public String prepareSelectCurrencyPage(HttpServletRequest req) {
        List<Currency> currencies = currencyDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_CURRENCY_LIST_NAME, currencies);
        return ServiceConstants.CURRENCY_SELECT_PAGE_PATH;
    }

    private String performCurrencySelect(HttpServletRequest req) {
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Account account = dataBean.getCurrentAccount();
        String  id = req.getParameter(ServiceConstants.PARAMETER_CURRENCY_ID_NAME);
        if(id != null) {
            long currencyId = Long.parseLong(id);
            Currency currency = currencyDAO.find(currencyId);
            account.setCurrency(currency);;
        }
        dataBean.setCurrentAccount(account);

        return ServiceConstants.ACCOUNT_EDIT_PAGE_PATH;
    }

    private String performTargetCurrencySelect(HttpServletRequest req) {
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        String  id = req.getParameter(ServiceConstants.PARAMETER_CURRENCY_ID_NAME);
        if(id != null) {
            long currencyId = Long.parseLong(id);
            Currency currency = currencyDAO.find(currencyId);
            dataBean.setTargetCurrency(currency);
        }

        return ServiceConstants.ACCOUNT_VIEW_PAGE_PATH;
    }

    private String fetchByBankId(HttpServletRequest req) {
        String  id = req.getParameter(ServiceConstants.PARAMETER_BANK_ID_NAME);
        List<Currency> currenciesList = null;
        if(id != null) {
            long bankId = Long.parseLong(id);
            currenciesList = currencyDAO.fetchByBankId(bankId);
        }
        req.setAttribute(ServiceConstants.ATTRIBUTE_CURRENCY_LIST_NAME, currenciesList);

        return ServiceConstants.CURRENCIES_PAGE_PATH;
    }

    private String performSaveCurrency(HttpServletRequest req) {
        String name = req.getParameter(ServiceConstants.PARAMETER_NAME);
        String rate = req.getParameter(ServiceConstants.PARAMETER_RATE);
        String precision = req.getParameter(ServiceConstants.PARAMETER_PRECISION);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        Currency currency = dataBean.getCurrentCurrency();
        boolean isNewCurrency = false;
        if(currency == null) {
            currency = new Currency();
            isNewCurrency = true;
        }

        currency.setName(name);

        if(rate != null) {
            currency.setRate(Float.parseFloat(rate));
        }
        if(precision != null) {
            currency.setPrecision(Integer.parseInt(precision));;
        }
        currency.setBank(dataBean.getCurrentBank());
        

        if (isNewCurrency) {
            currencyDAO.create(currency);
        } else {
            currencyDAO.edit(currency);
        }

        
        dataBean.setCurrentCurrency(null);
        List<Currency> currencies = currencyDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_CURRENCY_LIST_NAME, currencies);

        return ServiceConstants.CURRENCIES_PAGE_PATH;
    }
}
