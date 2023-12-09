package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DoctorDto;
import com.model.Doctor;
import com.response.DoctorResponse;
import com.service.DoctorService;

@RestController
@RequestMapping("/api/d1")
public class DoctorController {
	Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/saveDoctor")
	public ResponseEntity<?> createDoctor(@RequestBody DoctorDto doctorDto) {
		DoctorResponse doctorResponse = new DoctorResponse();

		try {

			Doctor saveDoctor = doctorService.saveDoctor(doctorDto);
			logger.info("doctor save successfully");

			doctorResponse.setData(saveDoctor);
			doctorResponse.setMessage("doctor save successfully");
			doctorResponse.setCode(HttpStatus.OK.toString());

			return ResponseEntity.internalServerError().body(doctorResponse);
		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(doctorResponse);
		}
	}

	@GetMapping(value = "/find/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable("doctorId") Long doctorId) {

		DoctorResponse doctorResponse = new DoctorResponse();

		try {
			Doctor doctor = doctorService.getDoctorById(doctorId);
			logger.info("doctor fetch successfully");
			doctorResponse.setData(doctor);
			doctorResponse.setCode(HttpStatus.OK.toString());
			doctorResponse.setMessage("doctor record is in database");

			return ResponseEntity.ok().body(doctorResponse);
		} catch (Exception e) {
			doctorResponse.setData(null);
			doctorResponse.setCode(HttpStatus.OK.toString());
			doctorResponse.setMessage("doctor record is not in database");

			return ResponseEntity.internalServerError().body("doctor trying to find is not found inside database!");

		}

	}

	@GetMapping(value = "/findall")
	public ResponseEntity<?> getAllDoctor() {
		try {
			List<Doctor> doctorList = doctorService.findAllDoctor();
			return ResponseEntity.ok().body(doctorList);

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("no doctor found");

		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> countDoctor() {
		try {
			long totalDoctor = doctorService.countDoctor();

			return ResponseEntity.ok().body("The total number of doctors present in database are " + totalDoctor);
		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("No doctor present");
		}
	}

	@GetMapping(value = "/findbyemail")
	public ResponseEntity<?> getDoctorWithEmail(@RequestParam("doctorEmail") String doctorEmail) {
		DoctorResponse doctorResponse = new DoctorResponse();
		try {
			Doctor doctor = doctorService.getDoctorWithEmail(doctorEmail);
			doctorResponse.setData(doctor);
			doctorResponse.setMessage("this data with email is in database");
			doctorResponse.setCode(HttpStatus.OK.toString());
			return ResponseEntity.ok().body(doctor);
		} catch (Exception e) {
			doctorResponse.setData(null);
			doctorResponse.setMessage("this data with email is not in database");
			doctorResponse.setCode(HttpStatus.OK.toString());
			return ResponseEntity.internalServerError().body("doctor with email : " + doctorEmail + " is not found");

		}
	}

	@DeleteMapping(value = "/delete/{doctorId}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable("doctorId") Long doctorId) {

		try {
			doctorService.deleteDoctorById(doctorId);

			return ResponseEntity.ok().body("The doctor with doctorId " + doctorId + "is deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("The doctor with doctorId " + doctorId + "is not found");

		}
	}
}
