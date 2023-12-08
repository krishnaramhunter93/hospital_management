package com.service;

import java.util.List;

import com.dto.PatientDto;
import com.model.Patient;

public interface PatientService {

	public Patient savePatient(PatientDto patientDto);

	List<Patient> getAllPatient();

	Patient savePatientFirst(Patient patient);

	Patient getPatientById(Long id);

	List<Patient> getAllPatientWithPagination(Integer pageNumber, Integer pagesize);

	public void deletePatientById(Long id);

	public long countPatient();

	public Patient updatePatient(Long id, PatientDto patientDto);

	public Patient getPatientByEmail(String email);

}
