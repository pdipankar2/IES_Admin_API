package com.tcs.service;

import java.util.List;

import com.tcs.binding.PlanForm;

public interface PlanService {
	
	public boolean createPlan(PlanForm planForm);
	
	public List<PlanForm> fatchPlan();
	
	public PlanForm getPlanById(Integer planId);
	
	public String changePlanStatus(Integer planId,String status);
	
	

}
