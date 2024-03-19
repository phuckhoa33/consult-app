package com.consult_app.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.forms.DoctorForm;
import com.consult_app.demo.models.Doctor;
import com.consult_app.demo.models.User;

@Mapper
public interface DoctorMapper {
	List<Doctor> getAllDoctors();

	DoctorForm getDoctorByUsername(@Param("username") String username);
	
	DoctorForm getDoctorByEmail(@Param("email") String email);

	Doctor getDoctorById(@Param("doctorId") Long doctorId);
	
	void updateDoctor(Doctor existingDoctor);

	void createDoctor(Doctor newDoctor);

//	boolean checkExistUserByEmail(String email);

}
