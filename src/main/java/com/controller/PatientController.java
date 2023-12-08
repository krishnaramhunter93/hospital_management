package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.PatientDto;
import com.model.Patient;
import com.service.PatientService;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/savePatient")
	public Patient createPatient(@RequestBody PatientDto patientDto) {
		Patient savePatient = patientService.savePatient(patientDto);
		return savePatient;

	}

	@PostMapping("/create")
	public Patient createPatient(@RequestBody Patient patient) {
		return patientService.savePatientFirst(patient);
	}

	@GetMapping("/findall")
	public List<Patient> getAllPatient() {
		List<Patient> patientList = patientService.getAllPatient();
		return patientList;
	}

	@GetMapping(value = "/find/{id}")
	public Patient getPatientById(@PathVariable("id") Long id) {
		Patient patient = patientService.getPatientById(id);
		return patient;
	}

	@GetMapping(value = "/findallwithpage")
	public List<Patient> getAllPatientWithPagination(@RequestParam("pageSize") Integer pagesize,
			@RequestParam("pageNumber") Integer pageNumber) {
		List<Patient> patienttList = patientService.getAllPatientWithPagination(pageNumber, pagesize);
		return patienttList;
	}

	@DeleteMapping("/delete/{id}")
	public String deletePatientById(@PathVariable("id") Long id) {
		patientService.deletePatientById(id);
		return "the record " + id + "has successfully deleted";
	}

	@GetMapping(value = "/count")
	public String countPatient() {
		long totalPatientCount = patientService.countPatient();
		return "The total Number of Patient Present in database are:" + totalPatientCount;
	}

	@PutMapping("/updatePatient/{id}")
	public Patient updatPatient(@PathVariable("id") Long id, @RequestBody PatientDto patientDto) {
		Patient updatePatient = patientService.updatePatient(id, patientDto);

		return updatePatient;
	}
	@GetMapping(value="/findbyemail")
	public ResponseEntity<?>getPatientByEmail(@RequestParam("email") String email){
		Patient patient=patientService.getPatientByEmail(email);
		return ResponseEntity.ok().body(null);
	}
}
