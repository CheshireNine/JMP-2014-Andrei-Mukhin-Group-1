package com.epam.concurrency.dao;

import java.util.List;

import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Currency;

public interface ICurrencyDAO {
	List<Currency> getList() throws DAOException;
	long save(Currency currency) throws DAOException;
	boolean remove(long id) throws DAOException;
	List<Currency> fetchByBankId(long id) throws DAOException ;

}
