package com.epam.employeemanagement.model;

public enum EmployeeStatus {
    FULL_TIME_EMPLOYEE("Full time"),
    PART_TIME_EMPLOYEE("Part time");
    
    private String statusName;
    
    private EmployeeStatus( String statusName )
    {
        this.statusName = statusName;
    }

    public String getStatus()
    {
        return statusName;
    }
}
