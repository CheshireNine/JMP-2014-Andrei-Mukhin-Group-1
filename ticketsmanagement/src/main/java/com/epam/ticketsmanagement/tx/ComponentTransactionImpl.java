package com.epam.ticketsmanagement.tx;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class ComponentTransactionImpl implements ComponentTransaction {
    private TransactionCallback logic;
    private XAConnection connection;
    private XAResourceTransaction txResource;

    public ComponentTransactionImpl(XAConnection connection, Xid xid, TransactionCallback callback) throws SQLException {
        this.connection = connection;

        XAResource resource = connection.getXAResource();
        this.logic = callback;
        this.txResource = new XAResourceTransaction(resource, xid);
    }

    @Override
    public void commit(boolean onePhase) throws TransactionException {
        txResource.commit(onePhase);
        
    }

    @Override
    public void end() throws TransactionException {
        txResource.suspend();
        
    }

    @Override
    public int prepare() throws TransactionException {
        return txResource.prepare();
    }

    @Override
    public void rollback() throws TransactionException {
        txResource.rollback();
    }

    @Override
    public void start() throws TransactionException {
        txResource.resume();
    }

    @Override
    public List<TransactionRecord> execute() throws TransactionException {
        List<TransactionRecord> auditLog = new ArrayList<TransactionRecord>();
        try {
            Connection connection = this.connection.getConnection();
            connection.setAutoCommit(false);
            auditLog = logic.execute(connection);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
        return auditLog;
    }
}
