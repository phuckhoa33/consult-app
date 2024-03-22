package com.consult_app.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.models.Doctor;

@Mapper
public interface DoctorMapper {
    Doctor getDoctorByDoctorId(@Param("doctorId") String doctorId);

    List<Doctor> getDoctors();

    void createDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);
}
