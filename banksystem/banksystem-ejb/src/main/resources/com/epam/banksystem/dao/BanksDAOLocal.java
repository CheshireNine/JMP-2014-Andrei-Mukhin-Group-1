package com.epam.banksystem.dao;

import java.util.List;

import javax.ejb.Local;

import com.epam.banksystem.model.Bank;

@Local
public interface BanksDAOLocal {
    Bank create(Bank bank);
    Bank edit(Bank bank);
    void remove(Bank bank);
    Bank find(Object id);
    List<Bank> findAll();
}
