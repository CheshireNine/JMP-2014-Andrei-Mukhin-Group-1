package test.com.epam.concurrency.dao;

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

import com.epam.concurrency.dao.CurrencyXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.jaxb.Currencies;
import com.epam.concurrency.utils.JAXBFileManager;

public class CurrencyDAOTest {
	private JAXBFileManager fileManager;
	private CurrencyXMLDAO dao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileManager = createMock("fileManager", JAXBFileManager.class);
		dao = new CurrencyXMLDAO();
		dao.setFileManager(fileManager);
	}

	@Test
	public void testGetList() {
		Currencies currencys = new Currencies();
		try {
			Capture<Class<Currencies>> classCapture = new Capture<Class<Currencies>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(currencys);
			replay(fileManager);

			List<Currency> currencyList = dao.getList();
			assertEquals(currencys.getCurrencies(), currencyList);
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
		Currencies currencys = new Currencies();
		List<Currency> currencyList = new ArrayList<Currency>();
		Currency firstCurrency = new Currency();
		Currency secondCurrency = new Currency();
		Currency thirdCurrency = new Currency();
		firstCurrency.setCurrencyId(1);
		secondCurrency.setCurrencyId(2);
		thirdCurrency.setCurrencyId(3);
		currencyList.add(firstCurrency);
		currencyList.add(secondCurrency);
		currencyList.add(thirdCurrency);
		currencys.setCurrencies(currencyList);
		Currency fourthCurrency = new Currency();
		try {
			Capture<Class<Currencies>> classCapture = new Capture<Class<Currencies>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(currencys);
			fileManager.marshal(anyObject(), capture(classCapture));
			replay(fileManager);

			long currencyId = dao.save(fourthCurrency);
			assertEquals(4, currencyId);
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

	@Test
	public void testFetchByBankId() {
		Currencies currencyts = new Currencies();
		List<Currency> currencytList = new ArrayList<Currency>();
		Currency firstCurrency = new Currency();
		Currency secondCurrency = new Currency();
		Bank firstBank = new Bank();
		Bank secondBank = new Bank();
		long firstBankId = 1;
		long secondBankId = 2;
		firstBank.setBankId(firstBankId);
		secondBank.setBankId(secondBankId);
		firstCurrency.setBank(firstBank);
		secondCurrency.setBank(secondBank);
		currencytList.add(firstCurrency);
		currencytList.add(secondCurrency);
		currencyts.setCurrencies(currencytList);
		try {
			Capture<Class<Currencies>> classCapture = new Capture<Class<Currencies>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(currencyts);
			replay(fileManager);

			List<Currency> fetchedList = dao.fetchByBankId(secondBankId);
			int banksNum = 1;
			assertEquals(banksNum, fetchedList.size());
			for(Currency currencyt : fetchedList) {
				assertEquals(secondBankId, currencyt.getBank().getBankId());
			}

		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

}
