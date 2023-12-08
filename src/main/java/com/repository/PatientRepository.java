package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Query(nativeQuery = true, value = "select * from Patient where email= :email")
	Patient findPatientByEmail(String email);

}
