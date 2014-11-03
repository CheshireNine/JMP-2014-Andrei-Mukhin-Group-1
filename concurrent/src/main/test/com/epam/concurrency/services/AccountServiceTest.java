/**
 * 
 */
package test.com.epam.concurrency.services;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.AccountXMLDAO;
import com.epam.concurrency.dao.IAccountDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.services.AccountService;

/**
 * @author I7eter
 *
 */
public class AccountServiceTest {
	private AccountService service;
	private IAccountDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = createMock("dao", AccountXMLDAO.class);
		service = new AccountService();
		service.setDao(dao);
	}

	/**
	 * Test method for {@link com.epam.concurrency.services.AccountService#getList()}.
	 */
	@Test
	public void testGetList() {
		List<Account> accounts = new ArrayList<Account>();
		Account account = new Account();
		accounts.add(account);
		try {
			expect(dao.getList()).andReturn(accounts);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		List<Account> currentList = service.getList();

		assertEquals(accounts, currentList);
	}

	/**
	 * Test method for {@link com.epam.concurrency.services.AccountService#addAccount(com.epam.concurrency.model.Currency, long)}.
	 */
	@Test
	public void testAddAccount() {
		long accountId = 44;
		try {
			expect(dao.save(anyObject(Account.class))).andReturn(accountId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		Currency currency = new Currency();
		long amount = 500;
		long currentId = service.addAccount(currency, amount);

		assertEquals(accountId, currentId);
	}

	/**
	 * Test method for {@link com.epam.concurrency.services.AccountService#fetchById(long)}.
	 */
	@Test
	public void testFetchById() {
		Account account = new Account();
		long accountId = 44;
		try {
			expect(dao.fetchById(accountId)).andReturn(account);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		Account findedAccount = service.fetchById(accountId);

		assertEquals(account, findedAccount);
	}

}
