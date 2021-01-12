package com.sandip.education.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.sandip.education.entity.Education;
import com.sandip.education.repository.EducationReporsitory;
import com.sandip.education.service.EducationService;
import com.sandip.webmodel.EducationWebModel;


@Service
@EnableAsync
public class EducationServiceimpl implements EducationService {

	@Autowired
	private EducationReporsitory educationReporsitory;
	
	@Override
	public List<Education> saveEduction(List<EducationWebModel> educations, Long userId) throws Exception {
		List<Education> educationsList = new ArrayList<>();
		for (EducationWebModel educationWebModel : educations) {
			Education education = new Education();
			BeanUtils.copyProperties(educationWebModel, education);
			education.setUserId(userId);
			educationsList.add(educationReporsitory.save(education));
		}
		return educationsList;
	}

	@Override
	public List<Education> readByIdEduction(Long id) throws Exception {
		return educationReporsitory.readByUserId(id);
	}

	@Override
	public List<Education> updateEduction(List<EducationWebModel> educations, Long id) throws Exception {
		educationReporsitory.deleteAll(educationReporsitory.readByUserId(id));
		List<Education> educationsList = new ArrayList<>();
		for (EducationWebModel educationWebModel : educations) {
			Education education = new Education();
			BeanUtils.copyProperties(educationWebModel, education);
			education.setUserId(id);
			educationsList.add(educationReporsitory.save(education));
		}
		return educationsList;
	}

}
