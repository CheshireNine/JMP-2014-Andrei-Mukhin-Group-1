package com.epam.ticketsmanagement.tx;

import java.util.List;

public interface ComponentTransaction {
    void commit(boolean onePhase) throws TransactionException;

    void end() throws TransactionException;

    int prepare() throws TransactionException;

    void rollback() throws TransactionException;

    void start() throws TransactionException;

    List<TransactionRecord> execute() throws TransactionException;
}
