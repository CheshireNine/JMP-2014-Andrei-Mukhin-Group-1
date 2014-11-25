package com.epam.banksystem.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.banksystem.model.Currency;

/**
 * Session Bean implementation class CurrencyDAO
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurrencyDAO extends AbstractFacade<Currency> implements CurrencyDAOLocal {

    @PersistenceContext(unitName = "banksystem-ejb")
    private EntityManager entityManager;

    public CurrencyDAO() {
        super(Currency.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
