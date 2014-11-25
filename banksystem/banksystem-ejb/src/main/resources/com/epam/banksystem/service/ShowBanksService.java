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

@Stateless
public class ShowBanksService implements ActionService {
    private static final Logger LOG = Logger.getLogger(ShowBanksService.class);
    @EJB
    BanksDAOLocal dao;

    public ShowBanksService() {
    }

    public String execute(HttpServletRequest req) {
        List<Bank> banks = dao.findAll();
        req.setAttribute(ServiceConstants.ATTRIBUTE_BANK_LIST_NAME, banks);
        initializeUserDataBean(req);
        return ServiceConstants.BANKS_PAGE_PATH;
    }

    private void initializeUserDataBean(HttpServletRequest req) {
        try {
            InitialContext ctx = new InitialContext();
            //Context envContext = (Context)ctx.lookup("java:comp/env");
            //UserData dataBean = (UserData)envContext.lookup("ejb/UserData");
            UserDataLocal dataBean = (UserDataLocal)ctx.lookup("java:comp/env/ejb/UserDataLocal");//"global/banksystem-ear-1.0/banksystem-ejb-1.0/UserData!com.epam.banksystem.form.UserDataLocal");
            HttpSession session = req.getSession(true);
            session.setAttribute(ServiceConstants.BEAN_USER_DATA_NAME, dataBean);
        } catch (Exception e) {
            LOG.error(e);
        }
    }
}
