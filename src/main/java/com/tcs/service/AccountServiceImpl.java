package com.tcs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.binding.UnlockAccountForm;
import com.tcs.binding.UserAccountFrom;
import com.tcs.entity.UserEntity;
import com.tcs.repo.UserRepo;
import com.tcs.util.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createAccout(UserAccountFrom accFrom) {

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(accFrom, entity);
		// set random pwd
		entity.setPwd(generatePwd());
		// set acc status
		entity.setAccStatus("LOCKED");
		entity.setActiveSw("Y");

		userRepo.save(entity);

		// send emails

		String subject = "";
		String body = "";

		return emailUtils.sendEmail(subject, body, accFrom.getEmail());

	}

	@Override
	public List<UserAccountFrom> fatchUserAccounts() {

		List<UserEntity> entitis = userRepo.findAll();

		List<UserAccountFrom> user = new ArrayList<>();
		for (UserEntity userEntity : entitis) {
			UserAccountFrom users = new UserAccountFrom();
			BeanUtils.copyProperties(userEntity, users);
			user.add(users);
		}

		return user;
	}

	@Override
	public UserAccountFrom getUserAccById(Integer accId) {
		Optional<UserEntity> optional = userRepo.findById(accId);

		if (optional.isPresent()) {

			UserEntity userEntity = optional.get();

			UserAccountFrom user = new UserAccountFrom();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}

		return null;
	}

	@Override
	public String changeAccStatus(Integer userId, String status) {

		Integer count = userRepo.updateAccStatus(userId, status);
		if (count > 0) {

			return "Status Changed";
		}

		return "Failed to Change";
	}

	@Override
	public String unlockUserAccount(UnlockAccountForm unlockAccForm) {

		UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());
		entity.setPwd(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepo.save(entity);

		return "Account Unlocked";
	}

	private String generatePwd() {

		String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String numbers = "0123456789";

		String alphaNumaric = allowedChars + numbers;

		StringBuffer sb = new StringBuffer();
		Random r = new Random();

		int length = 6;
		for (int i = 0; i < length; i++) {

			int index = r.nextInt(alphaNumaric.length());
			char randomChar = alphaNumaric.charAt(index);
			sb.append(randomChar);

		}

		return sb.toString();

	}

}
