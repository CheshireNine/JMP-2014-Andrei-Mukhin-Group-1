/**
 * 
 */
package test.com.epam.concurrency.dao;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.AccountXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.model.jaxb.Accounts;
import com.epam.concurrency.utils.JAXBFileManager;

/**
 * @author Petr_Tsiatnev
 *
 */
public class AccountDAOTest {

	private JAXBFileManager fileManager;
	private AccountXMLDAO dao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileManager = createMock("fileManager", JAXBFileManager.class);
		dao = new AccountXMLDAO();
		dao.setFileManager(fileManager);
	}

	/**
	 * Test method for {@link com.epam.concurrency.dao.AccountXMLDAO#getList()}.
	 */
	@Test
	public void testGetList() {
		Accounts accounts = new Accounts();
		try {
			Capture<Class<Accounts>> classCapture = new Capture<Class<Accounts>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(accounts);
			replay(fileManager);

			List<Account> accountList = dao.getList();
			assertEquals(accounts.getAccounts(), accountList);
		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.epam.concurrency.dao.AccountXMLDAO#save(com.epam.concurrency.model.Account)}.
	 */
	@Test
	public void testSave() {
		Accounts accounts = new Accounts();
		List<Account> accountList = new ArrayList<Account>();
		Account firstAccount = new Account();
		Account secondAccount = new Account();
		Account thirdAccount = new Account();
		firstAccount.setAccountId(1);
		secondAccount.setAccountId(2);
		thirdAccount.setAccountId(3);
		accountList.add(firstAccount);
		accountList.add(secondAccount);
		accountList.add(thirdAccount);
		accounts.setAccounts(accountList);
		Account fourthAccount = new Account();
		try {
			Capture<Class<Accounts>> classCapture = new Capture<Class<Accounts>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(accounts);
			fileManager.marshal(anyObject(), capture(classCapture));
			replay(fileManager);

			long accountId = dao.save(fourthAccount);
			assertEquals(4, accountId);
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

	/**
	 * Test method for {@link com.epam.concurrency.dao.AccountXMLDAO#fetchByPersonId(long)}.
	 */
	@Test
	public void testFetchByPersonId() {
		Accounts accounts = new Accounts();
		List<Account> accountList = new ArrayList<Account>();
		Account firstAccount = new Account();
		Account secondAccount = new Account();
		Person firstPerson = new Person();
		Person secondPerson = new Person();
		long firstPersonId = 1;
		long secondPersonId = 2;
		firstPerson.setPersonId(firstPersonId);
		secondPerson.setPersonId(secondPersonId);
		firstAccount.setOwner(firstPerson);
		secondAccount.setOwner(secondPerson);
		accountList.add(firstAccount);
		accountList.add(secondAccount);
		accounts.setAccounts(accountList);
		try {
			Capture<Class<Accounts>> classCapture = new Capture<Class<Accounts>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(accounts);
			replay(fileManager);

			List<Account> fetchedList = dao.fetchByPersonId(secondPersonId);
			int personsNum = 1;
			assertEquals(personsNum, fetchedList.size());
			for(Account account : fetchedList) {
				assertEquals(secondPersonId, account.getOwner().getPersonId());
			}

		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.epam.concurrency.dao.AccountXMLDAO#fetchById(long)}.
	 */
	@Test
	public void testFetchById() {
		Accounts accounts = new Accounts();
		List<Account> accountList = new ArrayList<Account>();
		Account firstAccount = new Account();
		Account secondAccount = new Account();

		long firstAccountId = 1;
		long secondAccountId = 2;

		firstAccount.setAccountId(firstAccountId);
		secondAccount.setAccountId(secondAccountId);
		accountList.add(firstAccount);
		accountList.add(secondAccount);
		accounts.setAccounts(accountList);
		try {
			Capture<Class<Accounts>> classCapture = new Capture<Class<Accounts>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(accounts);
			replay(fileManager);
			Account fetchedAccount = dao.fetchById(secondAccountId);
			assertEquals(secondAccountId, fetchedAccount.getAccountId());

		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

}
