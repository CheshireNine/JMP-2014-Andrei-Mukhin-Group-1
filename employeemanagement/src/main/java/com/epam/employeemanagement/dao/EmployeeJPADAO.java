package com.epam.employeemanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.epam.employeemanagement.model.Employee;


@Repository
public class EmployeeJPADAO extends AbstractFacade<Employee> implements EmployeeDAO {

    @PersistenceContext(unitName = "EmployeeManagement")
    private EntityManager entityManager;

    public EmployeeJPADAO() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
