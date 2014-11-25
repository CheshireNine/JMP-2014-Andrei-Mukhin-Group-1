/**
 * 
 */
package com.epam.banksystem.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

/**
 * @author Petr_Tsiatnev
 *
 */

public class ServiceLocator {

    private static final Logger LOG = Logger.getLogger(ServiceLocator.class);
    private static ServiceLocator instance;

    static {
        instance = new ServiceLocator();
    }
      
    private ServiceLocator() {
    }

    // returns the Service Locator instance 
    static public ServiceLocator getInstance() { 
        return instance;
    }


    // Services Constants Inner Class - service objects
    public enum Services {
        SHOW_BANKS,
        VIEW_BANK,
        ADD_ACCOUNT,
        EDIT_ACCOUNT
    };

    // Project EJB related constants
    @SuppressWarnings("rawtypes")
    private static final Class  SERVICES_CLASS = ActionService.class;
    private static final String SERVICE_NAME_TEMPLATE = "global/banksystem-ear-1.0/banksystem-ejb-1.0/${1}!com.epam.banksystem.service.ActionService";
    private static final String SHOW_BANKS_SERVICE_NAME = SERVICE_NAME_TEMPLATE.replace("${1}", "ShowBanksService");
    private static final String BANK_VIEW_SERVICE_NAME = SERVICE_NAME_TEMPLATE.replace("${1}", "ViewBankService");
    private static final String ACCOUNT_EDIT_SERVICE_NAME = SERVICE_NAME_TEMPLATE.replace("${1}", "EditAccountService");
    private static final String ACCOUNT_ADD_SERVICE_NAME = SERVICE_NAME_TEMPLATE.replace("${1}", "AddAccountService");

    // returns the JNDI name for the required service 
    static private String getServiceName(Services service){
        switch( service ) {
        case SHOW_BANKS:
            return SHOW_BANKS_SERVICE_NAME;
        case VIEW_BANK:
            return BANK_VIEW_SERVICE_NAME;
        case EDIT_ACCOUNT:
            return ACCOUNT_EDIT_SERVICE_NAME;
        case ADD_ACCOUNT:
            return ACCOUNT_ADD_SERVICE_NAME;
        }
        return null;
    }
      
    /* gets the EJBHome for the given service using the 
    ** JNDI name and the Class for the EJBHome
    */
    public ActionService getService( Services s ) 
            throws NamingException {
        ActionService service = null;
        try {
            Context initial  = new InitialContext();
            // Look up using the service name from 
            // defined constant
            LOG.info("Service requested: " + getServiceName(s));
            Object objref = initial.lookup(getServiceName(s));

            // Narrow using the service Class from 
            // defined constant
            Object obj = PortableRemoteObject.narrow(
                    objref, SERVICES_CLASS);
            service = (ActionService) obj;
        } catch( NamingException ex ) {
            ex.printStackTrace();
            LOG.error(ex);
            throw new NamingException();
        } catch( Exception ex ) {
            LOG.error(ex);
            throw new NamingException();
        }
        return service;
    }

}
