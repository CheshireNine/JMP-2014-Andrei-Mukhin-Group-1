package com.epam.banksystem.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Currency;
import com.epam.banksystem.model.Person;


/**
 * Session Bean implementation class AccountsDAO
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountsDAO extends AbstractFacade<Account> implements AccountsDAOLocal {

    @PersistenceContext(unitName = "banksystem-ejb")
    private EntityManager entityManager;

    public AccountsDAO() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Account> findAllByBankId(long bankId) {
        CriteriaQuery cq = (CriteriaQuery) entityManager
                .createQuery("from Account a where a.bank.bank_id = ?1")
                .setParameter(1, bankId).getResultList();
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public Account assignPerson(Account account, long personId) {
        Person person = getEntityManager().find(Person.class, personId);
        account.setOwner(person);
        return edit(account);
    }
    
    public Account assignCurrency(Account account, long currencyId) {
        Currency currency = getEntityManager().find(Currency.class, currencyId);
        account.setCurrency(currency);;
        return edit(account);
    }
}
