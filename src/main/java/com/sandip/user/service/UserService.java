package com.sandip.user.service;

import com.sandip.user.entity.User;
import com.sandip.webmodel.UserWebModel;

public interface UserService {

	User saveUser(UserWebModel user) throws Exception;

	
}
