package com.epam.concurrency.dao;

import java.util.List;

import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Account;

public interface IAccountDAO {
	List<Account> getList() throws DAOException;
	long save(Account account) throws DAOException;
	boolean remove(long id) throws DAOException;
	List<Account> fetchByPersonId(long id) throws DAOException;
	Account fetchById(long id) throws DAOException;
	boolean edit(Account account) throws DAOException;
}
