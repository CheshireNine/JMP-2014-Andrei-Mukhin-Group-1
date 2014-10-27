package com.epam.concurrency.dao;

import java.util.List;

import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;

public interface IPersonDAO {
	List<Person> getList() throws DAOException;
	long save(Person person) throws DAOException;
	boolean remove(long id) throws DAOException;

	//Person fetchById(long id) throws DAOException ;

}
