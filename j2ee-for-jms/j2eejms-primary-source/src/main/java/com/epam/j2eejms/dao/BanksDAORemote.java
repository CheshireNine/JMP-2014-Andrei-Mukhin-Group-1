package com.epam.j2eejms.dao;

import java.util.List;

import com.epam.j2eejms.model.Bank;

public interface BanksDAORemote {
    Bank create(Bank bank);
    Bank edit(Bank bank);
    void remove(Bank bank);
    Bank find(Object id);
    List<Bank> findAll();
}
