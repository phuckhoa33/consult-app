package com.consult_app.demo.services;

import java.util.List;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.models.Doctor;

public interface DoctorService {
    List<DoctorInformation> getDoctors(int range);

    Doctor getDoctorByDoctorId(String doctorId);

    List<String> getDistinctServicesOffered();

    String createDoctor(Doctor doctor);

    String updateDoctor(Doctor doctor);
}
