package com.consult_app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("{userId}")
    public String profile(@PathVariable String userId) {
        return "core/profile";
    }

    @GetMapping("edit/{userId}")
    public String edit(@PathVariable String userId) {
        return "core/edit-information";
    }
}
