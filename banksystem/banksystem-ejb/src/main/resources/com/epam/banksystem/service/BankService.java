package com.epam.banksystem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.banksystem.dao.BanksDAOLocal;
import com.epam.banksystem.form.UserDataLocal;
import com.epam.banksystem.model.Bank;
import com.epam.banksystem.requesthandler.HandlerConstants;

@Stateless
public class BankService implements ActionService {
    private static final Logger LOG = Logger.getLogger(BankService.class);
    @EJB
    BanksDAOLocal banksDAO;

    public BankService() {
    }

    public String execute(HttpServletRequest req) {
        String actionName = req.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        String page = null;
        if(HandlerConstants.PARAMETER_ACTION_VIEW_ALL.equals(actionName)) {
            page = prepareBanksPage(req);
        } else if(HandlerConstants.PARAMETER_ACTION_VIEW.equals(actionName)) {
            page = prepareBankViewPage(req);
        }

        return page;
    }

    public String prepareBankViewPage(HttpServletRequest req) {
        long bankId = Long.parseLong(req.getParameter(ServiceConstants.PARAMETER_ID_NAME));
        Bank bank = banksDAO.find(bankId);
        UserDataLocal dataBean = (UserDataLocal)req.getSession().getAttribute(ServiceConstants.BEAN_USER_DATA_NAME);
        dataBean.setCurrentBank(bank);
        return ServiceConstants.BANK_PAGE_PATH;
    }

    public String prepareBanksPage(HttpServletRequest req) {
        List<Bank> banks = banksDAO.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_BANK_LIST_NAME, banks);
        initializeUserDataBean(req);
        return ServiceConstants.BANKS_PAGE_PATH;
    }

    private void initializeUserDataBean(HttpServletRequest req) {
        try {
            InitialContext ctx = new InitialContext();
            //Context envContext = (Context)ctx.lookup("java:comp/env");
            //UserData dataBean = (UserData)envContext.lookup("ejb/UserData");
            UserDataLocal dataBean = (UserDataLocal)ctx.lookup("global/banksystem-ear-1.0/banksystem-ejb-1.0/UserData!com.epam.banksystem.form.UserDataLocal");
            HttpSession session = req.getSession(true);
            session.setAttribute(ServiceConstants.BEAN_USER_DATA_NAME, dataBean);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

}
