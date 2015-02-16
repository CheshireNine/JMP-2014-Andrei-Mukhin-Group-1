package com.epam.ticketsmanagement.tx;

public enum TxState {
    ACTIVE,
    MARKED_ABORT,
    LOCALLY_DONE,
    PREPARING,
    IN_DOUBT,
    ABORTING,
    COMMITTING,
    SUSPENDED,
    HEUR_COMMITTED,
    HEUR_ABORTED,
    HEUR_MIXED,
    HEUR_HAZARD,
    TERMINATED,
    COMMITTED,
    ABORTED;
}