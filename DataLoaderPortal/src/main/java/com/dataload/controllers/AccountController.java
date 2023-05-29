package com.dataload.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataload.constants.ResponseConstants;
import com.dataload.models.BaseResponse;
import com.dataload.models.LoginDetails;
import com.dataload.services.AccountService;
import com.dataload.utils.LoadExceptionHandler;

@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private AccountService service;

	@PostMapping(value = "/login", headers = "Accept=application/json")
	public BaseResponse<LoginDetails> login(@RequestBody LoginDetails loginDetails) {
		try {
			return service.login(loginDetails);
		} catch (LoadExceptionHandler e) {
			System.out.print("Exception occured while login using password the user controller");
		}
		return new BaseResponse<LoginDetails>(500, "Internal server error");
	}

//This is only for admin purpose 
	@PostMapping("/register")
	public BaseResponse<Void> registerAutor(@RequestBody LoginDetails loginDetails) {
		BaseResponse<Void> response = new BaseResponse<Void>(ResponseConstants.STATUS400, ResponseConstants.FAIL);
		try {
			response = service.registerAdmin(loginDetails);
		} catch (LoadExceptionHandler e) {
			logger.info("failed to register", e);
		}
		return response;
	}
	@GetMapping("/")
	public String test(){
		return "This string from backend";
	}

}
