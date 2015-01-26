package com.epam;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.epam.j2eejms.dao.BanksDAORemote;
import com.epam.j2eejms.model.Bank;
import com.epam.jms.producer.BanksJMSProducer;

public class ConsoleAppJMS {
    private static final Logger LOG = Logger.getLogger(ConsoleAppJMS.class);

    public ConsoleAppJMS() {
    }

    public void checkAppFunctionality() {
        //Create jms producer
        BanksJMSProducer jmsProducer = BanksJMSProducer.getInstance();

        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
 
        Context context = null;
        try {
            context = new InitialContext(jndiProperties);

            BanksDAORemote bankDAO = (BanksDAORemote)
                    context.lookup("ejb:j2eejms-ear-1.0/j2eejms-ejb-1.0/BanksDAO!com.epam.j2eejms.dao.BanksDAORemote");

            Bank bank = new Bank();
            bank.setName("Bank" + (new Date()).getTime());
            bankDAO.create(bank);
            jmsProducer.sendTextMessage("New bank added to DB: " + bank.getName());

        } catch (NamingException e) {
            LOG.info(e);
        } finally {
            try {
                context.close();
            } catch (NamingException e) {
                LOG.info(e);
            }
        }
        
    }

    public static void main(String[] args) {
        ConsoleAppJMS ejbClient = new ConsoleAppJMS();
        ejbClient.checkAppFunctionality();
    }

}
