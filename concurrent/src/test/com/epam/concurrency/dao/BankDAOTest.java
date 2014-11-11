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

import com.epam.concurrency.dao.BankXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.jaxb.Banks;
import com.epam.concurrency.utils.JAXBFileManager;

public class BankDAOTest {

	private JAXBFileManager fileManager;
	private BankXMLDAO dao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fileManager = createMock("fileManager", JAXBFileManager.class);
		dao = new BankXMLDAO();
		dao.setFileManager(fileManager);
	}

	@Test
	public void testGetList() {
		Banks banks = new Banks();
		try {
			Capture<Class<Banks>> classCapture = new Capture<Class<Banks>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(banks);
			replay(fileManager);

			List<Bank> bankList = dao.getList();
			assertEquals(banks.getBanks(), bankList);
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
		Banks banks = new Banks();
		List<Bank> bankList = new ArrayList<Bank>();
		Bank firstBank = new Bank();
		Bank secondBank = new Bank();
		Bank thirdBank = new Bank();
		firstBank.setBankId(1);
		secondBank.setBankId(2);
		thirdBank.setBankId(3);
		bankList.add(firstBank);
		bankList.add(secondBank);
		bankList.add(thirdBank);
		banks.setBanks(bankList);
		Bank fourthBank = new Bank();
		try {
			Capture<Class<Banks>> classCapture = new Capture<Class<Banks>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(banks);
			fileManager.marshal(anyObject(), capture(classCapture));
			replay(fileManager);

			long bankId = dao.save(fourthBank);
			assertEquals(4, bankId);
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
	public void testFetchById() {
		Banks banks = new Banks();
		List<Bank> bankList = new ArrayList<Bank>();
		Bank firstBank = new Bank();
		Bank secondBank = new Bank();

		long firstBankId = 1;
		long secondBankId = 2;

		firstBank.setBankId(firstBankId);
		secondBank.setBankId(secondBankId);
		bankList.add(firstBank);
		bankList.add(secondBank);
		banks.setBanks(bankList);
		try {
			Capture<Class<Banks>> classCapture = new Capture<Class<Banks>>();
			expect(fileManager.unmarshal(capture(classCapture))).andReturn(banks);
			replay(fileManager);
			Bank fetchedBank = dao.fetchById(secondBankId);
			assertEquals(secondBankId, fetchedBank.getBankId());

		} catch (JAXBException e) {
			fail("Exception handled");
			e.printStackTrace();
		} catch (DAOException e) {
			fail("Exception handled");
			e.printStackTrace();
		}
	}

}
