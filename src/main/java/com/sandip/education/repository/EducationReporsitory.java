package com.sandip.education.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sandip.education.entity.Education;


@Transactional
public interface EducationReporsitory extends JpaRepository<Education, Long>, JpaSpecificationExecutor<Education>{

}
