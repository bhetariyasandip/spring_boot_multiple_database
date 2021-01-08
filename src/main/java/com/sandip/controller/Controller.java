package com.sandip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandip.user.entity.User;
import com.sandip.user.service.UserService;
import com.sandip.webmodel.UserWebModel;

@RestController
@RequestMapping("api")
public class Controller {
	
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public User saveUser(@RequestBody UserWebModel user) throws Exception {
		return userService.saveUser(user);
	}
	
	@GetMapping(value = "/read/{id}")
    public User getChequeById(@PathVariable Long id) throws Exception{
		return null;
    }
}
