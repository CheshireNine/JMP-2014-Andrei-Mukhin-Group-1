package com.epam.ticketsmanagement.tx;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.apache.log4j.Logger;



public class XAResourceTransaction {

    public static final Logger LOGGER = Logger.getLogger(XAResourceTransaction.class);

    static final long serialVersionUID = 1L;

    private TxState state;
    private transient Xid xid;

    private transient XAResource xaresource;
    private transient int timeout;

    public XAResourceTransaction() {
    }

    public XAResourceTransaction(XAResource resource, Xid xid) {
        setResource(resource);
        setXid(xid);
        setState(TxState.ACTIVE);
    }


    private void setXid(Xid xid) {
        this.xid = xid;
    }

    void setResource(XAResource resource) {
        this.xaresource = resource;
    }

    void setState(TxState state) {
        this.state = state;
    }

    public synchronized void suspend() throws TransactionException {

        if (this.state.equals(TxState.ACTIVE)) {
            try {
                this.xaresource.end(this.xid, XAResource.TMSUCCESS);
                setState(TxState.SUSPENDED);
                LOGGER.info("Transaction ended: " + xid.getBranchQualifier());
            } catch (XAException xaerr) {
                LOGGER.error(xaerr.getMessage());
                throw new TransactionException(xaerr);
            }

        }
    }

    public synchronized void resume() throws TransactionException {

        try {
            LOGGER.info("Trying to start tx: " + xid.getBranchQualifier());
            this.xaresource.start(this.xid, XAResource.TMNOFLAGS);
            LOGGER.info("Transaction started: " + xid.getBranchQualifier());
        } catch (XAException xaerr) {
            LOGGER.error(xaerr.getMessage());
            throw new TransactionException(xaerr);
        }
        setState(TxState.ACTIVE);
    }

    public Object getState() {
        return this.state;
    }

    public synchronized int prepare() throws TransactionException {
        int ret = 0;

        if (TxState.ACTIVE.equals(this.state)) {
            suspend();
        }

        try {

            ret = this.xaresource.prepare(this.xid);
            LOGGER.info("Transaction prepared: " + xid.getBranchQualifier());
            setState(TxState.PREPARING);

        } catch (XAException xaerr) {
            LOGGER.error(xaerr.getMessage());
            throw new TransactionException(xaerr);
        }
        return ret;
    }

    public synchronized void rollback() throws TransactionException {

        try {

            if (this.state.equals(TxState.ACTIVE)) {
                suspend();
            }

            this.xaresource.rollback(this.xid);

        } catch (XAException resErr) {
            LOGGER.error(resErr.getMessage());
            throw new TransactionException(resErr);
        }
    }

    public synchronized void commit(boolean onePhase) throws TransactionException {

        try {

            if (TxState.ACTIVE.equals(this.state)) {
                suspend();
            }
        } catch (TransactionException re) {
            LOGGER.error(re.getMessage());
            throw new TransactionException(re);
        }

        try {

            this.xaresource.commit(this.xid, onePhase);

        } catch (XAException xaerr) {
            LOGGER.error(xaerr.getMessage());
            throw new TransactionException(xaerr);
        }
    }

    public void setXAResource(XAResource xaresource) {

        this.xaresource = xaresource;
        try {
            this.xaresource.setTransactionTimeout(this.timeout);
        } catch (XAException e) {

            LOGGER.error(e.getMessage());
        }

    }
}