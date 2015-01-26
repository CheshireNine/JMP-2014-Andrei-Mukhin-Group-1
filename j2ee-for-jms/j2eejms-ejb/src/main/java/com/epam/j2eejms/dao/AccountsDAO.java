package com.epam.j2eejms.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.epam.j2eejms.model.Account;
import com.epam.j2eejms.model.Currency;
import com.epam.j2eejms.model.Person;


/**
 * Session Bean implementation class AccountsDAO
 */
@Stateless
@LocalBean
@Remote(AccountsDAORemote.class)
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
