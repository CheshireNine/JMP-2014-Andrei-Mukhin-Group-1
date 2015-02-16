package com.epam.ticketsmanagement.tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class TransactionRecorder {
    private static final Logger LOG = Logger.getLogger(TransactionRecorder.class);

    @Resource(name = "auditDS")
    private DataSource dataSource;

    private List<TransactionRecord> records;

    public TransactionRecorder() {
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setRecords(List<TransactionRecord> records) {
        this.records = records;
    }

    public void processRecords() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            for(TransactionRecord record : records) {
                insert(connection, record);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    public void insert(Connection connection, TransactionRecord record) {

        String sql = "INSERT INTO audit " +
                "(`table_name`, `row_id`, `column_name`, `old_value`, `new_value`, `date`) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, record.getTableName());
            ps.setString(2, record.getRowId().toString());
            ps.setString(3, record.getColumnName());
            ps.setString(4, record.getOldValue().toString());
            ps.setString(5, record.getNewValue().toString());
            ps.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));

            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        } 
    }
}
