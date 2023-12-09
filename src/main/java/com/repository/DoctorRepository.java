package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query(nativeQuery = true, value = "select * from Doctor where doctor_email= :doctorEmail")
	Doctor findDoctorWithEmail(String doctorEmail);

}
