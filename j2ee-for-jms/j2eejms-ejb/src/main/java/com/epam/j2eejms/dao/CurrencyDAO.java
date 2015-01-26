package com.epam.j2eejms.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.j2eejms.model.Currency;

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

    @SuppressWarnings("unchecked")
    public List<Currency> fetchByBankId(long bankId) {
        Query query = entityManager.createQuery("select c from currency c where c.bank.bankId = :bankId", Currency.class);
        query.setParameter("bankId", bankId);
        return query.getResultList();
    }
}
