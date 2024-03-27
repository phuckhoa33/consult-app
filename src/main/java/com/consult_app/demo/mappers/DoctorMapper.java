package com.consult_app.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.models.Doctor;

@Mapper
public interface DoctorMapper {
    DoctorInformation getDoctorByDoctorId(@Param("doctorId") int doctorId);

    List<Doctor> getDoctors();

    List<DoctorInformation> searchDoctors(@Param("keyword") String keyword);

    void createDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);
}
