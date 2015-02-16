package com.epam.ticketsmanagement;

//import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.ticketsmanagement.dao.FilmSessionDAO;
import com.epam.ticketsmanagement.dao.TicketDAO;
import com.epam.ticketsmanagement.model.FilmSession;
import com.epam.ticketsmanagement.model.Ticket;
import com.epam.ticketsmanagement.model.TicketType;
import com.epam.ticketsmanagement.tx.ComponentTransaction;
import com.epam.ticketsmanagement.tx.ComponentTransactionImpl;
import com.epam.ticketsmanagement.tx.CompositeTransactionLogic;
import com.epam.ticketsmanagement.tx.ResourceXid;
import com.epam.ticketsmanagement.tx.TransactionCallback;
import com.epam.ticketsmanagement.tx.TransactionException;
import com.epam.ticketsmanagement.tx.TransactionRecord;
import com.epam.ticketsmanagement.tx.TransactionRecorder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testsApplicationContext.xml"})
public class TicketingTest {

    private static final Logger LOGGER = Logger.getLogger(TicketingTest.class);

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private FilmSessionDAO sessionDAO;

    @Resource(name="firstXADS")
    private XADataSource dataSourceOne;

    @Resource(name="secondXADS")
    private XADataSource dataSourceTwo;

    @Autowired
    private TransactionRecorder auditLogger;
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testXACompositeTransaction() throws SQLException, TransactionException {

        // create xa connections
        XAConnection xaConnectionOne = dataSourceOne.getXAConnection();
        XAConnection xaConnectionTwo = dataSourceTwo.getXAConnection();

        // create unique xids
        Xid xidOne = new ResourceXid(100, new byte[]{0x01}, new byte[]{0x02});
        Xid xidTwo = new ResourceXid(100, new byte[]{0x01}, new byte[]{0x03});

        // prepare data
        final Ticket ticket = new Ticket();
        ticket.setType(TicketType.BOOKED);
        FilmSession session = new FilmSession();
        session.setId(3);
        ticket.setPlace(4);
        ticket.setSession(session);

        // tx logic ( add new ticket)
        TransactionCallback logicOne = new TransactionCallback() {
            @Override
            public List<TransactionRecord> execute(Connection connection) {
                ticketDAO.insert(connection, ticket);

                // audit logic
                List<TransactionRecord> auditRecords = new ArrayList<TransactionRecord>();
                auditRecords.add(new TransactionRecord("Ticket", -1, "session_id", "", ticket.getSession().getId()));
                auditRecords.add(new TransactionRecord("Ticket", -1, "place", "", ticket.getPlace()));
                auditRecords.add(new TransactionRecord("Ticket", -1, "type", "", ticket.getType().getType()));
                return auditRecords;
                
            }
        };

        // tx logic (seats booking)
        TransactionCallback logicTwo = new TransactionCallback() {
            @Override
            public List<TransactionRecord> execute(Connection connection) {
                FilmSession session = ticket.getSession();
                session = sessionDAO.findById(connection, session.getId());

                session.setBooedSeats(session.getBooedSeats() + 1);

                sessionDAO.update(connection, session);

                // audit logic
                List<TransactionRecord> auditRecords = new ArrayList<TransactionRecord>();
                TransactionRecord record = new TransactionRecord("film_session", session.getId(), "booked_seats", session.getBooedSeats(), session.getBooedSeats() + 1);
                auditRecords.add(record);            

                return auditRecords;
                
            }
        };

        ComponentTransaction componentOne = new ComponentTransactionImpl(xaConnectionOne, xidOne, logicOne);
        ComponentTransaction componentTwo = new ComponentTransactionImpl(xaConnectionTwo, xidTwo, logicTwo);
        
        CompositeTransactionLogic compositeTx = new CompositeTransactionLogic();
        compositeTx.addParticipant(componentOne);
        compositeTx.addParticipant(componentTwo);

        compositeTx.start();
        auditLogger.setRecords(compositeTx.execute());
        compositeTx.end();
        
        int ret = compositeTx.prepare();

        if (ret == XAResource.XA_OK) {
            compositeTx.commit(false);
            auditLogger.processRecords();
            LOGGER.info("committed");
        } else {
            compositeTx.rollback();
            LOGGER.info("rolled back");
        }
    }

    @Test
    public void testXASingleTransaction() throws SQLException, TransactionException {

        // create xa connection
        XAConnection xaConnection = dataSourceOne.getXAConnection();

        // create unique xid
        Xid xid = new ResourceXid(100, new byte[]{0x01}, new byte[]{0x02});


        // prepare data
        final Ticket ticket = new Ticket();
        ticket.setType(TicketType.BOOKED);
        FilmSession session = new FilmSession();
        session.setId(5);
        ticket.setPlace(6);
        ticket.setSession(session);

        // tx logic
        TransactionCallback txLogic = new TransactionCallback() {
            @Override
            public List<TransactionRecord> execute(Connection connection) {
                FilmSession session = ticket.getSession();
                session = sessionDAO.findById(connection, session.getId());

                session.setBooedSeats(session.getBooedSeats() + 1);

                sessionDAO.update(connection, session);

                // audit logic
                List<TransactionRecord> auditRecords = new ArrayList<TransactionRecord>();
                TransactionRecord record = new TransactionRecord("film_session", session.getId(), "booked_seats", session.getBooedSeats(), session.getBooedSeats() + 1);
                auditRecords.add(record);            

                return auditRecords;
                
            }
        };

        // single tx component
        ComponentTransaction txSingleComponent = new ComponentTransactionImpl(xaConnection, xid, txLogic);

        // start transaction
        txSingleComponent.start();
        auditLogger.setRecords(txSingleComponent.execute());
        txSingleComponent.end();
        int ret = txSingleComponent.prepare();

        // final check
        if (ret == XAResource.XA_OK) {
           txSingleComponent.commit(false);
           auditLogger.processRecords();
           LOGGER.info("committed");
        } else {
           txSingleComponent.rollback();
           LOGGER.info("rolled back");
        }
    }

}
