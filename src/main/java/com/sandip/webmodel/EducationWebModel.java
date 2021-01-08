package com.sandip.webmodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public @Data class EducationWebModel {
	private String course;
	private String university_name;
	private Long passout_year;
	
}