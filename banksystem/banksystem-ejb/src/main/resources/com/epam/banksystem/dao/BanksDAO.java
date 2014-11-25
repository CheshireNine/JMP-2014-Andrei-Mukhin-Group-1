package com.epam.banksystem.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.banksystem.model.Bank;


/**
 * Session Bean implementation class BanksDAO
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BanksDAO extends AbstractFacade<Bank> implements BanksDAOLocal {

	@PersistenceContext(unitName = "banksystem-ejb")
    private EntityManager entityManager;

    public BanksDAO() {
    	super(Bank.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
