package com.epam.concurrency.services;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.concurrency.dao.BankXMLDAO;
import com.epam.concurrency.dao.IBankDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;

public class BankServiceTest {
	private BankService service;
	private IBankDAO dao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = createMock("dao", BankXMLDAO.class);
		service = new BankService();
		service.setDao(dao);
	}

	@Test
	public void testGetList() {
		List<Bank> banks = new ArrayList<Bank>();
		Bank bank = new Bank();
		banks.add(bank);
		try {
			expect(dao.getList()).andReturn(banks);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		List<Bank> currentList = service.getList();

		assertEquals(banks, currentList);
	}

	@Test
	public void testAddBank() {
		long bankId = 44;
		try {
			expect(dao.save(anyObject(Bank.class))).andReturn(bankId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		replay(dao);
		String name = "United Bank";
		long currentId = service.addBank(name);

		assertEquals(bankId, currentId);
	}

}
