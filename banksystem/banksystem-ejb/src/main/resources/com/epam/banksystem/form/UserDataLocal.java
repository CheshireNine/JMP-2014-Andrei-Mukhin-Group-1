package com.epam.banksystem.form;

import javax.ejb.Local;

import com.epam.banksystem.model.Account;
import com.epam.banksystem.model.Bank;
import com.epam.banksystem.model.Currency;
import com.epam.banksystem.model.Person;

@Local
public interface UserDataLocal {
    Bank getCurrentBank();

    void setCurrentBank(Bank currentBank);

    Account getCurrentAccount();

    void setCurrentAccount(Account currentAccount);

    Person getCurrentPerson();

    void setCurrentPerson(Person currentPerson);

    Currency getCurrentCurrency();

    void setCurrentCurrency(Currency currentCurrency);
}
