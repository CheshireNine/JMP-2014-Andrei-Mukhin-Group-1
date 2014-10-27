package com.epam.concurrency.dao;

import java.util.List;

import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;

public interface IBankDAO {
	List<Bank> getList() throws DAOException;
	long save(Bank bank) throws DAOException;
	boolean remove(long id) throws DAOException;
	Bank fetchById(long id) throws DAOException;

}
