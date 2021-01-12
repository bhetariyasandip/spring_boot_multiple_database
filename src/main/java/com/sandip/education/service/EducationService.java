package com.sandip.education.service;

import java.util.List;

import com.sandip.education.entity.Education;
import com.sandip.webmodel.EducationWebModel;

public interface EducationService {

	List<Education> saveEduction(List<EducationWebModel> educations, Long userId) throws Exception;

	List<Education> readByIdEduction(Long id) throws Exception;

	List<Education> updateEduction(List<EducationWebModel> educations, Long id) throws Exception;

	
}
