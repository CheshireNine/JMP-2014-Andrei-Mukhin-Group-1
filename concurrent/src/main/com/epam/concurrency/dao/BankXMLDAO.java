/**
 * BankXMLDAO
 */
package com.epam.concurrency.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.bind.JAXBException;

import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.jaxb.Banks;
import com.epam.concurrency.utils.JAXBFileManager;
import com.epam.concurrency.utils.ModelIdUtil;


/**
 * @author I7eter
 *
 */
public class BankXMLDAO implements IBankDAO {

	/**
	 * 
	 */
	private JAXBFileManager fileManager;

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();

	public BankXMLDAO() {
	}

	public void setFileManager(JAXBFileManager fileManager) {
		this.fileManager = fileManager;
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IBankDAO#getList()
	 */
	@Override
	public List<Bank> getList() throws DAOException {
		List<Bank> banks = null;
		try {
			readLock.lock();
			banks = unmarshal();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
		return banks;
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IBankDAO#save(com.epam.concurrency.model.Bank)
	 */
	@Override
	public long save(Bank bank) throws DAOException {
		
		List<Bank> storedBanks = getList();
		storedBanks = (storedBanks == null) ? new ArrayList<Bank>() : storedBanks;
		long curBankId = DAOConstants.DEFAULT_ID_NUM;
		if(storedBanks.size() > 0) {
			curBankId = ModelIdUtil.getMaxBankId(storedBanks) + 1;
		}
		/** obtain new bankId */
		bank.setBankId(curBankId);
		/** add to existed collection of banks */
		storedBanks.add(bank);

		writeLock.lock();
		try {
			marshal(storedBanks);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
		return bank.getBankId();
	}

	/* (non-Javadoc)
	 * @see com.epam.concurrency.dao.IBankDAO#remove(java.lang.long)
	 */
	@Override
	public boolean remove(long id) throws DAOException {
		List<Bank> storedBanks = getList();
		for(Bank bank : storedBanks) {
			if(bank.getBankId() == id) {
				storedBanks.remove(bank);
				writeLock.lock();
				try {
					marshal(storedBanks);
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
	public Bank fetchById(long id) throws DAOException {
		List<Bank> banks = getList();
		for(Bank bank : banks) {
			if(bank.getBankId() == id) {
				return bank;
			}
		}
		return new Bank();
	}

	private void marshal(List<Bank> banks)
			throws IOException, JAXBException {
		fileManager.marshal(new Banks(banks), Banks.class);
	}

	private List<Bank> unmarshal() throws JAXBException {
		Banks banks = ((Banks) fileManager
				.unmarshal(Banks.class));

		return (banks == null) ? new ArrayList<Bank>() : banks.getBanks();
	}
}
