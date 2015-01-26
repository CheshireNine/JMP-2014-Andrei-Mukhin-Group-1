package com.epam.j2eejms.dao;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.j2eejms.model.Bank;


/**
 * Session Bean implementation class BanksDAO
 */
@Stateless
@LocalBean
@Remote(BanksDAORemote.class)
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
