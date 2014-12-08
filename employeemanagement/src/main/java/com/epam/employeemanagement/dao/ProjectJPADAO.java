package com.epam.employeemanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.epam.employeemanagement.model.Project;


@Repository
public class ProjectJPADAO extends AbstractFacade<Project> implements ProjectDAO {

    @PersistenceContext(unitName = "EmployeeManagement")
    private EntityManager entityManager;

    public ProjectJPADAO() {
        super(Project.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
