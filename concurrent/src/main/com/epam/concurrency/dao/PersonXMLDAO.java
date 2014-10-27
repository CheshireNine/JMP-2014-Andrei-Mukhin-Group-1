/**
 * PersonXMLDAO
 */
package com.epam.concurrency.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.bind.JAXBException;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.model.jaxb.Persons;
import com.epam.concurrency.utils.ConfigurationManager;
import com.epam.concurrency.utils.JAXBSerializationHelper;
import com.epam.concurrency.utils.ModelIdUtil;


/**
 * @author I7eter
 *
 */
public final class PersonXMLDAO implements IPersonDAO {

	/**
	 * 
	 */
	private static File personFile = new File(
			ConfigurationManager.getProperty(ConfigurationManager.XML_PERSON_PATH));

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();

	public PersonXMLDAO() {
		
		
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IPersonDAO#getList()
	 */
	@Override
	public List<Person> getList() throws DAOException {
		List<Person> persons = null;
		readLock.lock();
		try {
			persons = unmarshal();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
		return persons;
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IPersonDAO#save(com.epam.concurrency.model.Person)
	 */
	@Override
	public long save(Person person) throws DAOException {
		List<Person> storedPersons = getList();
		storedPersons = (storedPersons == null) ? new ArrayList<Person>() : storedPersons;
		long curPersonId = DAOConstants.DEFAULT_ID_NUM;
		if(storedPersons.size() > 0) {
			curPersonId = ModelIdUtil.getMaxPersonId(storedPersons) + 1;
		}
		/** obtain new personId */
		person.setPersonId( curPersonId + 1);
		/** add to existed collection of persons */
		storedPersons.add(person);
		writeLock.lock();
		try {
			marshal(storedPersons);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
		return person.getPersonId();
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IPersonDAO#remove(java.lang.long)
	 */
	@Override
	public boolean remove(long id) throws DAOException {
		List<Person> storedPersons = getList();
		for(Person person : storedPersons) {
			if(person.getPersonId() == id) {
				storedPersons.remove(person);
				writeLock.lock();
				try {
					marshal(storedPersons);
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					writeLock.unlock();
				}
			}
		}
		return false;
	}

	public static void marshal(List<Person> persons)
			throws IOException, JAXBException {
		JAXBSerializationHelper.marshal(new Persons(persons), Persons.class, personFile);
	}

	public static List<Person> unmarshal() throws JAXBException {
		if(personFile.exists() && (personFile.length() > 0)) {

			Persons persons = ((Persons) JAXBSerializationHelper
					.unmarshal(Persons.class, personFile));
			return persons.getPersons();
		}

		return new ArrayList<Person>();
	}
}
