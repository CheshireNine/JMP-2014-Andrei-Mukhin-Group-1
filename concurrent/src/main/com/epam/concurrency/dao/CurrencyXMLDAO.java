/**
 * CurrencyXMLDAO
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
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.model.jaxb.Currencies;
import com.epam.concurrency.utils.ConfigurationManager;
import com.epam.concurrency.utils.JAXBSerializationHelper;
import com.epam.concurrency.utils.ModelIdUtil;


/**
 * @author I7eter
 *
 */
public final class CurrencyXMLDAO implements ICurrencyDAO {

	/**
	 * 
	 */
	private static File currencyFile = new File(
			ConfigurationManager.getProperty(ConfigurationManager.XML_CURRENCY_PATH));

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();

	public CurrencyXMLDAO() {
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.ICurrencyDAO#getList()
	 */
	@Override
	public List<Currency> getList() throws DAOException {
		List<Currency> currencies = null;
		readLock.lock();
		try {
			currencies = unmarshal();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
		return currencies;
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.ICurrencyDAO#save(com.epam.concurrency.model.Currency)
	 */
	@Override
	public long save(Currency currency) throws DAOException {
		List<Currency> storedCurrencies = getList();
		long curCurrencyId = 0L;
		if(storedCurrencies.size() > 0) {
			curCurrencyId = ModelIdUtil.getMaxCurrencyId(storedCurrencies);
		}
		/** obtain new currencyId */
		currency.setCurrencyId( curCurrencyId + 1);
		/** add to existed collection of currencies */
		storedCurrencies.add(currency);

		writeLock.lock();
		try {
			marshal(storedCurrencies);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
		return currency.getCurrencyId();
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.ICurrencyDAO#remove(java.lang.long)
	 */
	@Override
	public boolean remove(long id) throws DAOException {
		List<Currency> storedCurrencies = getList();
		for(Currency currency : storedCurrencies) {
			if(currency.getCurrencyId() == id) {
				storedCurrencies.remove(currency);
				writeLock.lock();
				try {
					marshal(storedCurrencies);
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

	@Override
	public List<Currency> fetchByBankId(long id) throws DAOException {
		List<Currency> fetchedCurrencies = new ArrayList<Currency>();

		List<Currency> allCurrencies = getList();
		for(Currency currency : allCurrencies) {
			if(currency.getBank().getBankId() == id) {
				fetchedCurrencies.add(currency);
			}
		}

		return fetchedCurrencies;
	}

	public static void marshal(List<Currency> currencies)
			throws IOException, JAXBException {
		JAXBSerializationHelper.marshal(new Currencies(currencies), Currencies.class, currencyFile);
	}

	public static List<Currency> unmarshal() throws JAXBException {
		if(currencyFile.exists() && (currencyFile.length() > 0)) {
			Currencies currencies = ((Currencies) JAXBSerializationHelper
					.unmarshal(Currencies.class, currencyFile));
			return currencies.getCurrencies();
		}

		return new ArrayList<Currency>();
	}
}
