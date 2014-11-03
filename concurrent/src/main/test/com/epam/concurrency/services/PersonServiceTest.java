package test.com.epam.concurrency.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.PersonXMLDAO;
import com.epam.concurrency.dao.IPersonDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.services.PersonService;

public class PersonServiceTest {
	private PersonService service;
	private IPersonDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = createMock("dao", PersonXMLDAO.class);
		service = new PersonService();
		service.setDao(dao);
	}

	@Test
	public void testGetList() {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		persons.add(person);
		try {
			expect(dao.getList()).andReturn(persons);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		List<Person> currentList = service.getList();

		assertEquals(persons, currentList);
	}

	@Test
	public void testAddPerson() {
		long personId = 44;
		try {
			expect(dao.save(anyObject(Person.class))).andReturn(personId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		String firstName = "firstName";
		String lastName = "lastName";
		long currentId = service.addPerson(firstName, lastName);

		assertEquals(personId, currentId);
	}

}
