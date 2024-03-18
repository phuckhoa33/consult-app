package com.consult_app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @GetMapping("/{doctorId}")
    public String displayDoctorDetail(@PathVariable String doctorId) {
        return "doctor/profile";
    }

    @GetMapping("appointment")
    public String displayAppointment() {
        return "doctor/inf-appointment";
    }

    @GetMapping("accepted-appointment")
    public String showAcceptedAppointment() {
        return "doctor/list_accept_appointment";
    }

    @GetMapping("refused-appointment")
    public String showRefusedAppointment() {
        return "doctor/list_refuse_appointment";
    }

    @GetMapping("analyze")
    public String showAssessmentDoctorPerformance() {
        return "doctor/charts_doctor";
    }
}
