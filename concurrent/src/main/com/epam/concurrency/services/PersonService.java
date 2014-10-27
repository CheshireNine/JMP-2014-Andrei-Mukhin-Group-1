package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IPersonDAO;
import com.epam.concurrency.dao.PersonXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;

public class PersonService {
	private static IPersonDAO dao = new PersonXMLDAO();

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
	public boolean addPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		try {
			dao.save(person);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean editPerson(int personId) {
		return true;
	}
	
	public boolean deletePerson(int personId) {
		return true;
	}

}
