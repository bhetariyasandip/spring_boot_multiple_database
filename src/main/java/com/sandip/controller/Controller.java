package com.sandip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody UserWebModel user,@PathVariable Long id) throws Exception {
		return userService.updateUser(user,id);
	}
	
	@GetMapping(value = "/read/{id}")
    public User readById(@PathVariable Long id) throws Exception{
		return userService.readById(id);
    }
	
	@GetMapping("/readAll")
	public List<User> readAll()throws Exception{
		return userService.readAll();
	}
}
