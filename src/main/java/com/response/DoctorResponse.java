package com.response;

import com.model.Doctor;

import lombok.Data;

@Data
public class DoctorResponse {
	private Doctor data;
	private String message;
	private String code;
}
