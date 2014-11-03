package test.com.epam.concurrency.services;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.BankXMLDAO;
import com.epam.concurrency.dao.CurrencyXMLDAO;
import com.epam.concurrency.dao.IBankDAO;
import com.epam.concurrency.dao.ICurrencyDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.services.CurrencyService;

public class CurrencyServiceTest {
	private CurrencyService service;
	private ICurrencyDAO currencyDAO;
	private IBankDAO bankDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currencyDAO = createMock("currencyDAO", CurrencyXMLDAO.class);
		bankDAO = createMock("bankDAO", BankXMLDAO.class);
		service = new CurrencyService();
		service.setDao(currencyDAO);
		service.setBankDAO(bankDAO);
	}

	@Test
	public void testGetList() {
		List<Currency> currencies = new ArrayList<Currency>();
		Currency currency = new Currency();
		currencies.add(currency);
		try {
			expect(currencyDAO.getList()).andReturn(currencies);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Exception handled");
		}
		replay(currencyDAO);
		List<Currency> currentList = service.getList();

		assertEquals(currencies, currentList);
	}

	@Test
	public void testAddCurrency() {
		long currencyId = 44;
		long bankId = 4;
		Bank bank = new Bank();
		try {
			expect(currencyDAO.save(anyObject(Currency.class))).andReturn(currencyId);
			expect(bankDAO.fetchById(bankId)).andReturn(bank);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Exception handled");
		}
		replay(currencyDAO, bankDAO);
		String currencyName = "USD";
		float rate = 1.3f;
		int precision = 2;
		long currentId = service.addCurrency(bankId,currencyName, rate, precision);

		assertEquals(currencyId, currentId);
	}

	@Test
	public void testFetchByBankId() {
		//long currencyId = 44;
		long bankId = 4;
		List<Currency> currencyList = new ArrayList<Currency>();
		Bank firstBank = new Bank();
		firstBank.setBankId(bankId);
		Currency firstCurrency = new Currency();
		Currency secondCurrency = new Currency();
		Currency thirdCurrency = new Currency();
		firstCurrency.setBank(firstBank);
		secondCurrency.setBank(firstBank);
		thirdCurrency.setBank(firstBank);
		currencyList.add(firstCurrency);
		currencyList.add(secondCurrency);
		currencyList.add(thirdCurrency);
		try {
			expect(currencyDAO.fetchByBankId(bankId)).andReturn(currencyList);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Exception handled");
		}
		replay(currencyDAO);

		List<Currency> fetchedList = service.fetchByBankId(bankId);

		int fetchedListSize = 3;

		assertEquals(fetchedListSize, fetchedList.size());
		for(Currency currency : fetchedList) {
			assertEquals(bankId, currency.getBank().getBankId());
		}

	}

}
