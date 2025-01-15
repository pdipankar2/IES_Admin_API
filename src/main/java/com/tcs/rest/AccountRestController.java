package com.tcs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.UserAccountFrom;
import com.tcs.service.AccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accService;

	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountFrom userAccountFrom) {
		boolean status = accService.createAccout(userAccountFrom);

		if (status) {
			return new ResponseEntity<String>("Account Creatd", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserAccountFrom>> getUsers() {
		List<UserAccountFrom> userAccFroms = accService.fatchUserAccounts();
		return new ResponseEntity<>(userAccFroms, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserAccountFrom> getUser(@PathVariable("userId") Integer userId) {
		UserAccountFrom userAccById = accService.getUserAccById(userId);
		return new ResponseEntity<UserAccountFrom>(userAccById, HttpStatus.OK);

	}

	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccountFrom>> updateUserAcc(@PathVariable("userId") Integer userId,
			@PathVariable("status") String status) {

		accService.changeAccStatus(userId, status);

		List<UserAccountFrom> userAccounts = accService.fatchUserAccounts();

		return new ResponseEntity<List<UserAccountFrom>>(userAccounts, HttpStatus.OK);

	}

}
