package com.epam.banksystem.dao;

import java.util.List;

import javax.ejb.Local;

import com.epam.banksystem.model.Account;

@Local
public interface AccountsDAOLocal {
    Account create(Account account);
    Account edit(Account account);
    void remove(Account account);
    Account find(Object id);
    List<Account> findAll();
    List<Account> findAllByBankId(long bankId);
}
