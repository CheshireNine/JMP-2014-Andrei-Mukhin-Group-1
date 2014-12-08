package com.epam.employeemanagement.requesthandler;

import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.employeemanagement.service.ActionService;




public class RequestHandler {

    private static Map<String, ActionService> services;
    private static final Logger LOG = Logger.getLogger(RequestHandler.class);

    public RequestHandler() {
    }

    public static void setServices(Map<String, ActionService> services) {
        RequestHandler.services = services;
    }

    public static String handleRequest(HttpServletRequest request) throws NamingException {
        String page;
        String actionName = request.getParameter(HandlerConstants.PARAMETER_ACTION_NAME);
        LOG.info("Service requested: " + actionName);
        ActionService service = services.get(actionName);
        page = service.execute(request);
        return page;
    }
}
