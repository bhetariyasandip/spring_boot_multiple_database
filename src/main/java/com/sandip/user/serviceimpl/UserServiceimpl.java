package com.sandip.user.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.sandip.education.entity.Education;
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
		List<Education> educations = educationService.saveEduction(userWeb.getEducations(), user.getId());
		user.setEducations(educations);
		return user;
	}
	
	
	

}
