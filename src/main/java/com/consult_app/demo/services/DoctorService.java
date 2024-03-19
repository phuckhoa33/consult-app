package com.consult_app.demo.services;

import java.util.List;

import com.consult_app.demo.forms.DoctorForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.Doctor;
import com.consult_app.demo.models.User;

import jakarta.validation.Valid;

public interface DoctorService {
    List<Doctor> getAllDoctors();

	User getUserByEmail(String email);

	Doctor createDoctor(DoctorForm doctorForm);

	Doctor getDoctorById(Long doctorId);
	
	boolean updateDoctor(Long doctorId, DoctorForm doctorForm);


	boolean checkExistUser(DoctorForm doctorForm);

}