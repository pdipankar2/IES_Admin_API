package com.tcs.service;

import com.tcs.binding.DashbordCard;
import com.tcs.binding.LoginForm;

public interface UserService {
	
	public String  Login(LoginForm loginForm);
	
	public boolean recoverPwd(String email);
	
	public DashbordCard fatchDashbordInfo();
	
	

}
