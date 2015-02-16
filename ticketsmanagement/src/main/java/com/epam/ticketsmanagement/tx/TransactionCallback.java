package com.epam.ticketsmanagement.tx;

import java.sql.Connection;
import java.util.List;

public abstract class TransactionCallback {
    public abstract List<TransactionRecord> execute(Connection connection);
}
