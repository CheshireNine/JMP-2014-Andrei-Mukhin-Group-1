package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IPersonDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;

public class PersonService {
	private IPersonDAO dao;

	public PersonService() {
		super();
	}

	public List<Person> getList() {
		List<Person> persons = null;
		try {
			persons = dao.getList();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}
	public long addPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		long personId = 0;
		try {
			personId = dao.save(person);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personId;
	}
	
	public boolean editPerson(int personId) {
		return true;
	}
	
	public boolean deletePerson(int personId) {
		return true;
	}

	public void setDao(IPersonDAO dao) {
		this.dao = dao;
	}

}
