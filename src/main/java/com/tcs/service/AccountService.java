package com.tcs.service;

import java.util.List;

import com.tcs.binding.UserAccountFrom;

public interface AccountService {

	public String createAccout(UserAccountFrom accFrom);
	
	public List<UserAccountFrom> fatchUserAccounts();
	
	public UserAccountFrom getUserAccById(Integer accId);
	
	public String changeAccStatus(Integer accId,String status);
	
	
}
