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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.isAuthenticated());
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
    public Object getAppointment(@PathVariable String doctorId, Model model) {
        DoctorInformation doctor = doctorService.getDoctorByDoctorId(doctorId);
        String[] doctorTimestamps = new String[] { "8:00", "8:30", "8:45", "9:00", "9:30", "10:00", "10:30", "10:45",
                "11:00", "11:30", "11:45", "12:00", "12:30", "12:45" };
        model.addAttribute("doctor", doctor);
        model.addAttribute("timestamp", doctorTimestamps);
        return "core/details_doctor";
    }

    @GetMapping("appointment/{doctorId}/{time}")
    public Object appointment(@PathVariable("doctorId") String doctorId, @PathVariable("time") String time) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName().equals("anonymousUser")) {
            return new RedirectView("/auth/login");
        }
        return "core/appointment";
    }

}
