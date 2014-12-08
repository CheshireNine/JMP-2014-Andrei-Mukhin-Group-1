package com.epam.employeemanagement.servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.employeemanagement.requesthandler.RequestHandler;

public class GeneralServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(GeneralServlet.class);

    public GeneralServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        String page = null;
        try {          
          page = RequestHandler.handleRequest(req);
          LOG.info("page: " + page);

        } catch (NamingException e) {
            LOG.error(e);
        }
        // dispatch control to view
        dispatch(req, resp, page);
    }

    protected void dispatch(HttpServletRequest req, HttpServletResponse resp,
            String page) throws javax.servlet.ServletException, 
            java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

}
