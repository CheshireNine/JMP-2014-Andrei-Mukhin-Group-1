package com.epam.banksystem.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.banksystem.requesthandler.RequestHandler;

public class GeneralServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(GeneralServlet.class);

    @EJB
    private RequestHandler handler;

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

            /**ApplicationResources provides a simple API 
             * for retrieving constants and other 
             * preconfigured values**/
//          ApplicationResources resource = 
//            ApplicationResources.getInstance();
//        InitialContext initialContext = null;
//        BeanManager beanManager = null;
//        try {
//            initialContext = new InitialContext();
//            beanManager = (BeanManager) initialContext.lookup("java:comp/BeanManager");
//        } catch (NamingException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
        
        //beanManager.toString();
        try {

            // Look up using the service name from 
            // defined constant
          
          page = handler.handleRequest(req);
          LOG.info("page: " + page);

        } catch (NamingException e) {
            LOG.error(e);
//            page = resource.getErrorPage(e);
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
