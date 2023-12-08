package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Patient")
@Data
public class Patient {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "f_Name", nullable = false)
	private String firstName;
	@Column(name = "l_Name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "address", nullable = false)
	private String address;

	

	


}
