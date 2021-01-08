package com.sandip.webmodel;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public @Data class UserWebModel {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileNo;
	private List<EducationWebModel> educations;
}