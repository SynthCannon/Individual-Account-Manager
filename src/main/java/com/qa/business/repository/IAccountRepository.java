package com.qa.business.repository;

public interface IAccountRepository {
	
	String getAllAccounts();

	String getAccount(Long id);

	String createAccount(String accountJSON);
	
	String deleteAccount(Long id);
	
	String updateAccount(String accountJSON);
}

