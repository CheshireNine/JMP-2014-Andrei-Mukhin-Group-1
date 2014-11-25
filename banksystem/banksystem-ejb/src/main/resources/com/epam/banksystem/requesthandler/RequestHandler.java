package com.epam.banksystem.requesthandler;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.banksystem.service.ActionService;
import com.epam.banksystem.service.ServiceLocator;
import com.epam.banksystem.service.ServiceLocator.Services;


@Stateless
public class RequestHandler {

    private Map<String, Services> serviceNames;
    private static final Logger LOG = Logger.getLogger(RequestHandler.class);

    public RequestHandler() {
        serviceNames = new HashMap<String, Services>();
        serviceNames.put(HandlerConstants.REQUEST_BANKS_PAGE, Services.SHOW_BANKS);
        serviceNames.put(HandlerConstants.REQUEST_BANK_VIEW__PAGE, Services.VIEW_BANK);
        serviceNames.put(HandlerConstants.REQUEST_ACCOUNT_EDIT_PAGE, Services.EDIT_ACCOUNT);
        serviceNames.put(HandlerConstants.REQUEST_ACCOUNT_ADD_PAGE, Services.ADD_ACCOUNT);
    }

    public String handleRequest(HttpServletRequest request) throws NamingException {
        ServiceLocator locator = ServiceLocator.getInstance();
        String page;
        String actionName = request.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        LOG.info("Action requested: " + actionName);
        ActionService service = locator.getService(serviceNames.get(actionName));
        page = service.execute(request);
        return page;
    }
}
