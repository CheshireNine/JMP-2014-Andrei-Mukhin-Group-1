package com.epam.employeemanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.epam.employeemanagement.model.Unit;


@Repository
public class UnitJPADAO extends AbstractFacade<Unit> implements UnitDAO {

    @PersistenceContext(unitName = "EmployeeManagement")
    private EntityManager entityManager;

    public UnitJPADAO() {
        super(Unit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
