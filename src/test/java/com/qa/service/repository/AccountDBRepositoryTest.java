package com.qa.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {

	@InjectMocks
	private AccountDBRepository repo;
	
	@Mock
	private Query query;
	
	@Mock
	private EntityManager manager;
	
	private JSONUtil util;
	
	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";
	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";
	
	@Test
	public void createAccountTest() {
		String mockAccount = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(mockAccount, "{\"message\":\"Account created \"}");
	}
	
	@Test
	public void getAllAccountsTest() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("John", "Doe", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}
		
	@Test
	public void updateAccountSuccessTest() {
		String test = repo.updateAccount(MOCK_OBJECT);
		Assert.assertEquals(test, "{\"message\": \"Account sucessfully updated\"}");
	}
	
	@Test
	public void updateAccountFailTest() {
		String test = repo.updateAccount(null);
		Assert.assertEquals(test, "{\"message\": \"Original account does not exist\"}");
	}

	@Test
	public void DeleteAccountSuccessTest() {
		String test = repo.deleteAccount(1L);
		Assert.assertEquals(test, "{\"message\":\"Account deleted \"}");
	}
	
	@Test
	public void DeleteAccountFailTest() {
		String test = repo.deleteAccount(null);
		Assert.assertEquals(test, "{\"message\":\"Account doesnt exist \"}");
	}

}



