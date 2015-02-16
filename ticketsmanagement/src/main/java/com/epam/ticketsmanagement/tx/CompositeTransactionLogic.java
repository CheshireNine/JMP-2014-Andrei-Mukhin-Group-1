package com.epam.ticketsmanagement.tx;

import java.util.List;

public class CompositeTransactionLogic implements ComponentTransaction {

    private CustomCoordinator coordinator;

    public CompositeTransactionLogic() {
        coordinator = new CustomCoordinator();
    }

    public void addParticipant(ComponentTransaction participant) {
        coordinator.addParticipant(participant);
    }

    @Override
    public void commit(boolean onePhase) throws TransactionException {
        coordinator.commit();
    }

    @Override
    public void end() throws TransactionException {
        coordinator.end();
        
    }

    @Override
    public int prepare() throws TransactionException {
        return coordinator.prepare();
    }

    @Override
    public void rollback() throws TransactionException {
        coordinator.rollback();
        
    }

    @Override
    public void start() throws TransactionException {
        coordinator.start();
        
    }

    @Override
    public List<TransactionRecord> execute() throws TransactionException {
        return coordinator.execute();
    }

}
