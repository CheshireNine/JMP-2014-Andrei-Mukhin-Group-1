package com.epam.banksystem.dao;

import java.util.List;

import javax.ejb.Local;

import com.epam.banksystem.model.Currency;

@Local
public interface CurrencyDAOLocal {
    Currency create(Currency currency);
    Currency edit(Currency currency);
    void remove(Currency currency);
    Currency find(Object id);
    List<Currency> findAll();
    List<Currency> fetchByBankId(long bankId);
}
