package com.epam.employeemanagement.form;

import com.epam.employeemanagement.model.Employee;


public class UserData {

    private Employee currentEmployee;

    public UserData() {
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }
 
}
