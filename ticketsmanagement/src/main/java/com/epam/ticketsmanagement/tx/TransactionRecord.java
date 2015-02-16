package com.epam.ticketsmanagement.tx;

public class TransactionRecord {

    private String tableName;
    private Object rowId;
    private String columnName;
    private Object oldValue;
    private Object newValue;

    public TransactionRecord(){
    }

    public TransactionRecord(String tableName, Object rowId, String columnName, Object oldValue, Object newValue) {
        super();
        this.tableName = tableName;
        this.rowId = rowId;
        this.columnName = columnName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getRowId() {
        return rowId;
    }

    public void setRowId(Object rowId) {
        this.rowId = rowId;
    }
    
}
