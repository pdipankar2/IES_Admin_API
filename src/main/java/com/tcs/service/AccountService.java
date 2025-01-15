package com.tcs.service;

import java.util.List;

import com.tcs.binding.UnlockAccountForm;
import com.tcs.binding.UserAccountFrom;

public interface AccountService {

	public boolean createAccout(UserAccountFrom accFrom);
	
	public List<UserAccountFrom> fatchUserAccounts();
	
	public UserAccountFrom getUserAccById(Integer accId);
	
	public String changeAccStatus(Integer userId,String status);
	
    public String unlockUserAccount(UnlockAccountForm unlockAccForm);

}
