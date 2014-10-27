package com.epam.concurrency.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationManager {
	
	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;	
	private static final String RESOURCE_NAME = "resources.configuration";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, DEFAULT_LOCALE);

	public static final String XML_PERSON_PATH = "xml.person.path";
	public static final String XML_ACCOUNT_PATH = "xml.account.path";
	public static final String XML_BANK_PATH = "xml.bank.path";
	public static final String XML_CURRENCY_PATH = "xml.currency.path";
	public static final String LOG4J_FILE_CONF_PATH = "log4j.file.path";

	private ConfigurationManager() {
	}
	
	public static String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
