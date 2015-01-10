package com.epam.employeemanagement.service;

import java.util.List;

import com.epam.employeemanagement.model.Employee;

public interface EmployeeService {
    List<Employee> list(int pageNum);
    Employee view(Long employeeId);
    Employee save(Employee employee);
    void delete(Long employeeId);
    boolean exists(Long employeeId);
    long count();
    int getPageSize();
    List<Employee> findByName(String name, int pageNum);
    long countByName(String name);
    Employee addProject(Employee employee, long projectId);
}
