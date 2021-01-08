package com.sandip.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sandip.education.entity.Education;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public @Data class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String mobileNo;
			
	private Date createdon;
	
	@Transient
	private List<Education> educations;
	
}