package com.consult_app.demo.services;

import java.util.List;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.models.Doctor;

public interface DoctorService {
    List<DoctorInformation> getDoctors(int range, String filter);

    Doctor getDoctorByDoctorId(String doctorId);

    List<String> getDistinctServicesOffered();

    List<DoctorInformation> searchDoctors(String keyword);

    String createDoctor(Doctor doctor);

    String updateDoctor(Doctor doctor);

    int getDoctorPageAmount(List<Doctor> doctors);
}
