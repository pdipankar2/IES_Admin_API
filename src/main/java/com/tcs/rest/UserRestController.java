package com.tcs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.DashbordCard;
import com.tcs.binding.LoginForm;
import com.tcs.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {

		String status = userService.Login(loginForm);

		if (status.equals("Success")) {
			// login success
			return "redirect:/dashbord";

		} else {
			return status;
		}
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashbordCard> buildDashbord() {

		DashbordCard dashbordInfo = userService.fatchDashbordInfo();

		return new ResponseEntity<DashbordCard>(dashbordInfo, HttpStatus.OK);
	}

}
