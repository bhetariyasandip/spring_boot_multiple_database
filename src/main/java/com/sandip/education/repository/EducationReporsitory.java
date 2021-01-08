package com.sandip.education.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sandip.education.entity.Education;


@Transactional
public interface EducationReporsitory extends JpaRepository<Education, Long>, JpaSpecificationExecutor<Education>{
	
	@Query(value="SELECT ed FROM Education ed WHERE ed.userId = ?1")
	List<Education> readByUserId(Long id);

}
