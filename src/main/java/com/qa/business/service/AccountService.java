package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepository;

public class AccountService implements IAccountService {

	@Inject
	private IAccountRepository repo;

	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	public String getAccount(Long id) {
		return repo.getAccount(id);
	}

	public String createAccount(String jsonAccount) {
		return repo.createAccount(jsonAccount);
	}

	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}
	
	public String updateAccount(String jsonAccount) {
		return repo.updateAccount(jsonAccount);
	}

	

}

