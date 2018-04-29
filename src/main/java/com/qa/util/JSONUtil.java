package com.qa.util;

import java.util.Map;

import com.google.gson.Gson;
import com.qa.persistence.domain.Account;

public class JSONUtil {

	private Gson gson;
	
	public JSONUtil() 
	{
	this.gson = new Gson();
	}
	
	public String getJSONForObject(Object obj) 
	{
	return gson.toJson(obj);
	}
	
	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) 
	{
	return gson.fromJson(jsonString, clazz);
	}

	public String getJSONForObject(Map<Long, Account> accountMap) {
		return gson.toJson(accountMap);
	}
	
}
