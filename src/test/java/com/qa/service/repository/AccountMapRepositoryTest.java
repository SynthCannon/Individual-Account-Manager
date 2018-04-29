package com.qa.service.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import com.qa.business.repository.AccountMapRepository;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;


public class AccountMapRepositoryTest {

	AccountMapRepository account = new AccountMapRepository();
	JSONUtil util = new JSONUtil();
	Account AC1 = new Account("John", "Smith", "2222");
	Account AC2 = new Account("Jim", "Smith", "3333");
	Account AC3 = new Account("Bob", "Smith", "4444");
	String test1 = util.getJSONForObject(AC1);
	String test2 = util.getJSONForObject(AC2);
	String test3 = util.getJSONForObject(AC3);
	
	
	@Test
	public void createAndDeleteAccountTest() {
		
	assertEquals(account.getCount(), 0);
	account.createAccount(test1);
	assertEquals(account.getCount(), 1);
	account.createAccount(test1);
	assertEquals(account.getCount(), 2);
	account.createAccount(test3);
	assertEquals(account.getCount(), 3);
	account.deleteAccount(AC2.getId());
	assertEquals(account.getCount(), 2);
	account.deleteAccount(AC1.getId());
	assertEquals(account.getCount(), 1);
	}
	
	@Test
	public void checkJsonStringTest() {
		
	account.createAccount(test1);
	String expected = "{\"1\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"2\":{\"firstName\":\"John\",\"secondName\":\"Smith\",\"accountNumber\":\"2222\"}}";
	
	String Test = util.getJSONForObject(account.getAccountMap());
	assertEquals(expected, Test);
	}
	
	@Test
	public void updateAccountTest() {
		
		String Actual = account.updateAccount(test1);
		assertEquals("{\"message\": \"Account sucessfully updated\"}", Actual);	
	}
	
}

