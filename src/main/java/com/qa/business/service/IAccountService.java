package com.qa.business.service;

public interface IAccountService {
	String getAllAccounts();

	String getAccount(Long id);

	String createAccount(String jsonAccount);
	
	String deleteAccount(Long id);
	
	String updateAccount(String jsonAccount);
}

