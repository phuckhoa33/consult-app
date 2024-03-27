package com.consult_app.demo.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.mappers.DoctorMapper;
import com.consult_app.demo.models.Doctor;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.DoctorService;
import com.consult_app.demo.services.UserService;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    UserService userService;

    @Override
    public List<DoctorInformation> getDoctors(int range, String filter) {
        List<Doctor> doctors = doctorMapper.getDoctors();

        int endIndex;
        int startIndex;
        if (!filter.equals("none")) {
            doctors = doctors.stream()
                    .filter(doctor -> doctor.getServicesOffered().contains(filter))
                    .collect(Collectors.toList());

            endIndex = doctors.size();
            startIndex = 0;
        } else {
            endIndex = range * 8;
            startIndex = endIndex - 8;
        }
        int pages = getDoctorPageAmount(doctors);

        List<DoctorInformation> doctorInformations = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            Doctor currentDoctor = doctors.get(i);
            User user = userService.getUserByUserId(String.valueOf(currentDoctor.getUserId()));
            String fullname = user.getFirstname() + user.getLastname();

            DoctorInformation doctorInfor = DoctorInformation.builder()
                    .doctorId(currentDoctor.getDoctorId())
                    .education(currentDoctor.getEducation())
                    .bioId(currentDoctor.getBioId())
                    .experience(currentDoctor.getExperience())
                    .rating(currentDoctor.getRating())
                    .servicesOffered(currentDoctor.getServicesOffered())
                    .fullname(fullname)
                    .userId(currentDoctor.getUserId())
                    .pages(pages)
                    .build();
            doctorInformations.add(doctorInfor);
        }

        return doctorInformations;
    }

    @Override
    public DoctorInformation getDoctorByDoctorId(String doctorId) {
        return doctorMapper.getDoctorByDoctorId(Integer.parseInt(doctorId));
    }

    @Override
    public String createDoctor(Doctor doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDoctor'");
    }

    @Override
    public String updateDoctor(Doctor doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDoctor'");
    }

    @Override
    public List<String> getDistinctServicesOffered() {
        List<Doctor> doctors = doctorMapper.getDoctors();
        List<String> servicesOffereds = new ArrayList<>();

        for (int i = 0; i < doctors.size(); i++) {
            String servicesOffered = doctors.get(i).getServicesOffered();
            servicesOffereds.add(servicesOffered);
        }
        Set<String> distinctServices = Set.copyOf(servicesOffereds);
        return List.copyOf(distinctServices);
    }

    @Override
    public int getDoctorPageAmount(List<Doctor> doctors) {
        int doctorAmount = doctors.size();

        int pageAmount = (int) Math.ceil(doctorAmount / 8);

        return pageAmount;
    }

    @Override
    public List<DoctorInformation> searchDoctors(String keyword) {
        return doctorMapper.searchDoctors(keyword);
    }

}
