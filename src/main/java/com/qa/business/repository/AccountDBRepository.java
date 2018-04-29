package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;
import static javax.transaction.Transactional.TxType.REQUIRED;

public class AccountDBRepository implements IAccountRepository {

	
	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	public EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	
	public String getAllAccounts() {
		LOGGER.info("AccountDBRepository getAllAccounts");
		Query query = manager.createQuery("Select m FROM Account m");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}


	public String getAccount(Long id) {
		Account aAccount = findAccount(id);
		return util.getJSONForObject(aAccount);
	}

	private Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}

	@Transactional(REQUIRED)
	public String createAccount(String accountJSON) {
		Account aAccount = util.getObjectForJSON(accountJSON, Account.class);
		manager.persist(aAccount);
		return "{\"message\":\"Account created \"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = findAccount(id);
		System.out.println("AccountDBRepository deleteAccount accountInDB " + accountInDB);
		if (id != null) {
			manager.remove(accountInDB);
			return "{\"message\":\"Account deleted \"}";
		}
		else {
			return "{\"message\":\"Account doesnt exist \"}";
		}
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(String accountJSON) {
		Account aAccount = util.getObjectForJSON(accountJSON, Account.class);
		if (accountJSON != null) {
			manager.merge(aAccount);
			return "{\"message\": \"Account sucessfully updated\"}";
		}
		else {
			return "{\"message\": \"Original account does not exist\"}";
		}
		
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
		
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}

