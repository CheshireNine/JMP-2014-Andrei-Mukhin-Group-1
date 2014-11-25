package com.epam.banksystem.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.banksystem.model.Person;

/**
 * Session Bean implementation class PersonsDAO
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonsDAO extends AbstractFacade<Person> implements PersonsDAOLocal {

    @PersistenceContext(unitName = "banksystem-ejb")
    private EntityManager entityManager;

    public PersonsDAO() {
        super(Person.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
