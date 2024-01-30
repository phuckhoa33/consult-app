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
        return "core/details_doctor";
    }

    @GetMapping("appointment")
    public String displayAppointment() {
        return "core/appointment";
    }
}
