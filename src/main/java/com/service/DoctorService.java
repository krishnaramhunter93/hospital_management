package com.service;

import com.dto.DoctorDto;
import com.model.Doctor;

public interface DoctorService {

	Doctor saveDoctor(DoctorDto doctorDto);

	Doctor getDoctorById(Long doctorId);

	long countDoctor();

}
