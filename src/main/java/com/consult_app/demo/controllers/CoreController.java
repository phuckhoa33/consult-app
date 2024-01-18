package com.consult_app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/consultapp")
public class CoreController {
    @GetMapping("")
    public String index(Model model) {
        return "core/home";
    }
}
