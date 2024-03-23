package com.consult_app.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.services.DoctorService;

import jakarta.ws.rs.Path;

@Controller
@RequestMapping("/")
public class CoreController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("")
    public String index(Model model) {
        return "core/index";
    }

    @GetMapping("/doctors/{page}/{filter}")
    public String displayDoctors(Model model, @PathVariable("page") String page,
            @PathVariable("filter") String filter) {

        List<DoctorInformation> doctors = doctorService.getDoctors(Integer.parseInt(page), filter);
        List<String> services = doctorService.getDistinctServicesOffered();
        model.addAttribute("doctors", doctors);
        model.addAttribute("services", services);
        model.addAttribute("amount", doctors.get(0).getPages());
        model.addAttribute("currentPage", page.trim());
        return "core/list_doctor";
    }

    @GetMapping("schedule-consult")
    public String getAppointment() {

        return "core/appointment";
    }

}
