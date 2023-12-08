package com.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DoctorDto;

import com.model.Doctor;
import com.repository.DoctorRepository;
import com.service.DoctorService;
@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);


	@Override
	public Doctor saveDoctor(DoctorDto doctorDto) {
		Doctor doctor=new Doctor();
		doctor.setDoctorName(doctorDto.getDoctorName());
		doctor.setDoctorEmail(doctorDto.getDoctorEmail());
		doctor=doctorRepository.save(doctor);
		
		if(doctor==null) {
			throw new  RuntimeException("doctor is not save");
		}
		return doctor;
	}


	@Override
	public Doctor getDoctorById(Long doctorId) {

		Optional<Doctor>dOptional	=doctorRepository.findById(doctorId);
		if(!dOptional.isPresent()) {
			throw new RuntimeException("Doctor not found in database");
		}
		Doctor doctor=dOptional.get();
		logger.info("getDoctor method has ended");
		return doctor;
	}

}