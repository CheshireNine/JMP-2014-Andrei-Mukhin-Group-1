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
        serviceNames.put(HandlerConstants.BANK_SERVICE_NAME, Services.BANK);
        serviceNames.put(HandlerConstants.ACCOUNT_SERVICE_NAME, Services.ACCOUNT);
        serviceNames.put(HandlerConstants.PERSON_SERVICE_NAME, Services.PERSON);
        serviceNames.put(HandlerConstants.CURRENCY_SERVICE_NAME, Services.CURRENCY);
    }

    public String handleRequest(HttpServletRequest request) throws NamingException {
        ServiceLocator locator = ServiceLocator.getInstance();
        String page;
        String actionName = request.getParameter(HandlerConstants.PARAMETER_SERVICE_NAME);
        LOG.info("Service requested: " + actionName);
        ActionService service = locator.getService(serviceNames.get(actionName));
        page = service.execute(request);
        return page;
    }
}
