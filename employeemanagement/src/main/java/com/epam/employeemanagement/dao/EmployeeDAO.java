package com.epam.employeemanagement.dao;

import java.util.List;

import com.epam.employeemanagement.model.Employee;


public interface EmployeeDAO {
    Employee create(Employee employee);
    Employee edit(Employee employee);
    void remove(Employee employee);
    Employee find(Object id);
    List<Employee> findAll();
}
