package com.qa.business.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Transactional(SUPPORTS)
@Alternative
public class AccountMapRepository implements IAccountRepository{

	private Map<Long, Account> accountMap = new HashMap<Long, Account>();
	
	private Long ID = 1L;
	
	private int count = 0;
	
	private void initAccountMap() {
		Account account = new Account("Joe", "Bloggs", "1234");
		getAccountMap().put(1L, account);
	}


    private JSONUtil util = new JSONUtil();
    
    public int getCount() {
    	
        return count;	
        
        }
	
	public String getAllAccounts() {
		return util.getJSONForObject(getAccountMap().values());
	}

	public String getAccount(Long id) {
		
		return null;
	}

	@Transactional(REQUIRED)
	public String createAccount(String accountJSON) {
		count = count + 1;
		initAccountMap();
		ID = ID + 1;
		Account aAccount = util.getObjectForJSON(accountJSON, Account.class);
		getAccountMap().put(ID, aAccount);
		return "{\"message\": \"Account sucessfully created\"}";
			
		}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		count = count - 1;
    	if (id != null) {
    		getAccountMap().remove(id);
    		return "{\"Account Deleted\"}";
		}
		else {
			return "{\"Cant Delete\":\" Account does not exist\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateAccount(String accountJSON) {
		Account aAccount = util.getObjectForJSON(accountJSON, Account.class);
		getAccountMap().put(aAccount.getId(), aAccount);
		return "{\"message\": \"Account sucessfully updated\"}";
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}

}


