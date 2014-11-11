package com.epam.concurrency.dao;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.PersonXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.model.jaxb.Persons;
import com.epam.concurrency.utils.JAXBFileManager;

public class PersonDAOTest {
	
	private JAXBFileManager fileManager;
	private PersonXMLDAO dao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileManager = createMock("fileManager", JAXBFileManager.class);
		dao = new PersonXMLDAO();
		dao.setFileManager(fileManager);
	}

	@Test
	public void testGetList() {
		Persons persons = new Persons();
		try {
			Capture<Class<Persons>> classCapture = new Capture<Class<Persons>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(persons);
			replay(fileManager);

			List<Person> personList = dao.getList();
			assertEquals(persons.getPersons(), personList);
		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		Persons persons = new Persons();
		List<Person> personList = new ArrayList<Person>();
		Person firstPerson = new Person();
		Person secondPerson = new Person();
		Person thirdPerson = new Person();
		firstPerson.setPersonId(1);
		secondPerson.setPersonId(2);
		thirdPerson.setPersonId(3);
		personList.add(firstPerson);
		personList.add(secondPerson);
		personList.add(thirdPerson);
		persons.setPersons(personList);
		Person fourthPerson = new Person();
		try {
			Capture<Class<Persons>> classCapture = new Capture<Class<Persons>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(persons);
			fileManager.marshal(anyObject(), capture(classCapture));
			replay(fileManager);

			long personId = dao.save(fourthPerson);
			assertEquals(4, personId);
		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

}
