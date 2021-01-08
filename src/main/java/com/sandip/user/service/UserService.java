package com.sandip.user.service;

import java.util.List;

import com.sandip.user.entity.User;
import com.sandip.webmodel.UserWebModel;

public interface UserService {

	User saveUser(UserWebModel user) throws Exception;

	User readById(Long id) throws Exception;

	List<User> readAll() throws Exception;

	
}
