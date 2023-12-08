package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dto.PatientDto;

import com.model.Patient;
import com.repository.PatientRepository;
import com.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Patient savePatient(PatientDto patientDto) {

		Patient patient = new Patient();
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setEmail(patientDto.getEmail());
		patient.setAddress(patientDto.getAddress());
		patient = patientRepository.save(patient);

		return patient;

	}

	@Override
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		List<Patient> pList = patientRepository.findAll();
		return pList;
	}

	@Override
	public Patient savePatientFirst(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	public Patient getPatientById(Long id) {

		Optional<Patient> pOptional = patientRepository.findById(id);
		Patient patient = pOptional.get();

		return patient;
	}

	public List<Patient> getAllPatientWithPagination(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Page<Patient> pagePatient = patientRepository.findAll(PageRequest.of(pageNumber, pageSize));

		// we need to convert page to list of customer and return list as result
		List<Patient> patientList = new ArrayList<Patient>();

		for (Patient pat : pagePatient) {
			patientList.add(pat);
		}
		return patientList;

	}

	@Override
	public void deletePatientById(Long id) {
		patientRepository.deleteById(id);

	}

	@Override
	public long countPatient() {

		long totalPatient = patientRepository.count();

		return totalPatient;
	}

	@Override
	public Patient updatePatient(Long id, PatientDto patientDto) {
		// TODO Auto-generated method stub
		
		Optional<Patient>patientOptional=patientRepository.findById(id);
		Patient updatePatient=null;
		
		if(patientOptional.isPresent()) {
			Patient oldPatient=patientOptional.get();
			oldPatient.setAddress(patientDto.getAddress());
			oldPatient.setEmail(patientDto.getEmail());
			oldPatient.setFirstName(patientDto.getFirstName());
			oldPatient.setLastName(patientDto.getFirstName());
			
			updatePatient=patientRepository.save(oldPatient);
		}
		return updatePatient;
	}

	@Override
	public Patient getPatientByEmail(String email) {
		Patient patient=patientRepository.findPatientByEmail(email);
		return patient;
	}

}
