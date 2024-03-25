package com.consult_app.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.consult_app.demo.dtos.DoctorInformation;
import com.consult_app.demo.services.DoctorService;

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
            @PathVariable("filter") String filter, String keyword) {
        List<DoctorInformation> doctors = new ArrayList<>();
        if (keyword == null) {
            doctors = doctorService.getDoctors(Integer.parseInt(page), filter);
        } else {
            doctors = doctorService.searchDoctors(keyword);
        }

        List<String> services = doctorService.getDistinctServicesOffered();
        model.addAttribute("doctors", doctors);
        model.addAttribute("services", services);
        model.addAttribute("amount", doctors.get(0).getPages());
        model.addAttribute("currentPage", page.trim());
        return "core/list_doctor";
    }

    @GetMapping("schedule-consult/{doctorId}")
    public Object getAppointment(@PathVariable String doctorId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "core/appointment";
        }

        return new RedirectView("/auth/login");
    }

}
