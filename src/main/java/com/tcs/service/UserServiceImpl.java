package com.tcs.service;

import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.binding.DashbordCard;
import com.tcs.binding.LoginForm;
import com.tcs.entity.EligEntity;
import com.tcs.entity.UserEntity;
import com.tcs.repo.EligEntityRepo;
import com.tcs.repo.PlanRepo;
import com.tcs.repo.UserRepo;
import com.tcs.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private EligEntityRepo eligEntityRepo;

	@Override
	public String Login(LoginForm loginForm) {

		UserEntity entity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {
			return "Invalid credintial";
		}

		if ("Y".equals(entity.getActiveSw()) && "UNLOCKED".equals(entity.getAccStatus())) {

			return "Success";

		} else {
			return "Account Locked/In-Active";
		}

	}

	@Override
	public boolean recoverPwd(String email) {

		UserEntity byEmail = userRepo.findByEmail(email);
		if (null == byEmail) {

			return false;
		} else {
			String subject = "";
			String body = "";

			return emailUtils.sendEmail(subject, body, email);
		}

	}

	@Override
	public DashbordCard fatchDashbordInfo() {

		long planCount = planRepo.count();

		List<EligEntity> elegList = eligEntityRepo.findAll();

		long aprovedcount = elegList.stream().filter(e -> e.getPlanStatus().equals("AP")).count();

		long deniedCount = elegList.stream().filter(e -> e.getPlanStatus().equals("DN")).count();

		double benefitAmt = elegList.stream().mapToDouble(e -> e.getBenefitAmt()).sum();

		DashbordCard card = new DashbordCard();
		card.setPlansCnt(planCount);
		card.setApprovedCnt(aprovedcount);
		card.setDeniedCnt(deniedCount);
		card.setBenifitAmtGiven(benefitAmt);

		return card;
	}

}
