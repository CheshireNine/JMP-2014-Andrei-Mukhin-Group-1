package com.epam.banksystem.form;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Bank;
import com.epam.banksystem.model.Currency;
import com.epam.banksystem.model.Person;

/**
 * Session Bean implementation class UserData
 */
@SessionScoped
@Stateful
public class UserData implements UserDataLocal {

    private Bank currentBank;
    private Account currentAccount;
    private Person currentPerson;
    private Currency currentCurrency;

    public UserData() {
    }

    public Bank getCurrentBank() {
        return currentBank;
    }

    public void setCurrentBank(Bank currentBank) {
        this.currentBank = currentBank;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public Currency getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(Currency currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

}
