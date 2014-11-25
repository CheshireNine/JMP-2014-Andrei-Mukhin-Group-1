/**
 * 
 */
package com.epam.banksystem.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import com.epam.banksystem.service.ShowBanksService;


/**
 * @author Petr_Tsiatnev
 *
 */

public class ServiceLocator {

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
        SHOW_BANKS
    };

    // Project EJB related constants
    @SuppressWarnings("rawtypes")
    final static Class  SHOW_BANKS_SERVICE_CLASS = ShowBanksService.class;
    final static String SHOW_BANKS_SERVICE_NAME = "ShowBanksService";


    // Returns the Class for the required service 
    @SuppressWarnings("rawtypes")
    static private Class getServiceClass(Services service){
        switch( service ) {
        case SHOW_BANKS:
            return SHOW_BANKS_SERVICE_CLASS;
        }
        return null;
    }
      
    // returns the JNDI name for the required service 
    static private String getServiceName(Services service){
        switch( service ) {
        case SHOW_BANKS:
            return SHOW_BANKS_SERVICE_NAME;
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
            Object objref = initial.lookup(getServiceName(s));

            // Narrow using the service Class from 
            // defined constant
            Object obj = PortableRemoteObject.narrow(
                    objref, getServiceClass(s));
            service = (ActionService) obj;
        } catch( NamingException ex ) {
            ex.printStackTrace();
            //TODO logging
            throw new NamingException();
        } catch( Exception ex ) {
            //TODO logging
            throw new NamingException();
        }
        return service;
    }

}
