package com.epam.ticketsmanagement.tx;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CustomCoordinator {
    private static final Logger LOGGER = Logger.getLogger(CustomCoordinator.class);

    private List<ComponentTransaction> participants;

    
    public CustomCoordinator() {
        super();
        participants = new ArrayList<ComponentTransaction>();
    }
    public void addParticipant(ComponentTransaction participant) {
        participants.add(participant);
        
    }
    public void commit() throws TransactionException {
        for(ComponentTransaction participant : participants) {
            participant.commit(false);
        }
    }

    public void start() throws TransactionException {
        for(ComponentTransaction participant : participants) {
            participant.start();
        }
    }

    public void end() throws TransactionException {
        for(ComponentTransaction participant : participants) {
            participant.end();
        }
        
    }

    public int prepare() throws TransactionException {
        int commonStatus = -1;
        for(ComponentTransaction participant : participants) {
            if(commonStatus == -1) {
                commonStatus = participant.prepare();
            } else if(participant.prepare() != commonStatus) {
                throw new TransactionException("Statusses are different");
            }
        };
        return commonStatus;
    }

    public void rollback() throws TransactionException {
        for(ComponentTransaction participant : participants) {
            participant.rollback();
        }
    }

    public List<TransactionRecord> execute() throws TransactionException {
        List<TransactionRecord> auditLog = new ArrayList<TransactionRecord>();
        try {
            for(ComponentTransaction participant : participants) {
                auditLog.addAll(participant.execute());
            }
        } catch (TransactionException e) {
            LOGGER.error(e.getMessage());
            throw new TransactionException(e);
        }
        return auditLog;
    }
}