package com.epam.banksystem.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class AccountService
 */
//@Stateless
//@LocalBean
public class AccountService {

    /**
     * Default constructor. 
     */
    public AccountService() {
        // TODO Auto-generated constructor stub
    }

//	public List<Account> getList() {
//		List<Account> accounts = null;
//		try {
//			accounts = dao.getList();
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return accounts;
//	}
//
//	public long addAccount(Currency currency, long amount) {
//		Account account = new Account();
//		account.setCurrency(currency);
//		account.setAmount(amount);
//		long accountId = 0;
//		try {
//			accountId = dao.save(account);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return accountId;
//	}
//	
//	public Account fetchById(long id) {
//		try {
//			return dao.fetchById(id);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return new Account();
//	}
//	
//	public boolean editAccount(Account account) {
//		try {
//			return dao.edit(account);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public boolean deleteAccount(int accountId) {
//		try {
//			return dao.remove(accountId);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public void setDao(IAccountDAO dao) {
//		this.dao = dao;
//	}
}
