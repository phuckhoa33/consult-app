package com.consult_app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.services.DoctorService;


@Controller
@RequestMapping("/core")
public class CoreController {
	
	@Autowired
    DoctorService doctorService;
	
    @GetMapping("/index")
    public String index(Model model) {
        return "core/index";
    }
}
