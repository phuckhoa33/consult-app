package com.consult_app.demo.controllers;

import org.apache.sis.util.resources.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.consult_app.demo.forms.SignupForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthController {
    @GetMapping("login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        SignupForm user = new SignupForm();
        model.addAttribute("user", user);
        return "auth/signup";
    }

    @PostMapping("signup")
    public ModelAndView signup(
            @ModelAttribute("user") @Valid SignupForm user,
            HttpServletRequest request, Errors errors) {

        System.out.println(user.getEmail());

        return new ModelAndView();
    }

}
