package com.epam.j2eejms.dao;

import java.util.List;

import com.epam.j2eejms.model.Account;

public interface AccountsDAORemote {
    Account create(Account account);
    Account edit(Account account);
    void remove(Account account);
    Account find(Object id);
    List<Account> findAll();
    Account assignPerson(Account account, long personId);
    Account assignCurrency(Account account, long currencyId);
}
