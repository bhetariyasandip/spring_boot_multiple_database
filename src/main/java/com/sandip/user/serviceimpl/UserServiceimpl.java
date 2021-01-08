package com.sandip.user.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.sandip.education.service.EducationService;
import com.sandip.user.entity.User;
import com.sandip.user.repository.UserReporsitory;
import com.sandip.user.service.UserService;
import com.sandip.webmodel.UserWebModel;


@Service
@EnableAsync
public class UserServiceimpl implements UserService {
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private UserReporsitory userReporsitory;

	@Override
	public User saveUser(UserWebModel userWeb) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		BeanUtils.copyProperties(userWeb, user);
		user.setCreatedon(new Date());
		user=userReporsitory.save(user);
		user.setEducations(educationService.saveEduction(userWeb.getEducations(), user.getId()));
		return user;
	}

	@Override
	public User readById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userReporsitory.findById(id);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			user.setEducations(educationService.readByIdEduction(id));
			return user;
		}
		return null;
	}

	@Override
	public List<User> readAll() throws Exception {
		// TODO Auto-generated method stub
		List<User> userLst = userReporsitory.findAll();
		List<User> userList = new ArrayList<>(); 
		for (User user : userLst) {
			user.setEducations(educationService.readByIdEduction(user.getId()));
			userList.add(user);
		}
		return userList;
	}
	
	
	

}
