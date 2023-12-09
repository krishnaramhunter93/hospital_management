package com.service;

import java.util.List;

import com.dto.DoctorDto;
import com.model.Doctor;

public interface DoctorService {

	Doctor saveDoctor(DoctorDto doctorDto);

	Doctor getDoctorById(Long doctorId);

	long countDoctor();

	List<Doctor> findAllDoctor();

	Doctor getDoctorWithEmail(String email);

	void deleteDoctorById(Long doctorId);

}
