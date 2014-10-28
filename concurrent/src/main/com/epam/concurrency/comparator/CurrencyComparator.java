/**
 * 
 */
package com.epam.concurrency.comparator;

import java.util.Comparator;

import com.epam.concurrency.model.Currency;

/**
 * @author Petr_Tsiatnev
 *
 */
public final class CurrencyComparator implements Comparator<Currency> {

	/**
	 * 
	 */
	public CurrencyComparator() {
	}

	@Override
	public int compare(Currency o1, Currency o2) {
		return o1.getCurrencyId() < o2.getCurrencyId() ? -1 :
			o1.getCurrencyId() > o2.getCurrencyId() ? 1 : 0;
	}

}
