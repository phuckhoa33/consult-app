package com.consult_app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.dtos.TokenChecker;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.ResetPasswordForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AuthService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("login")
    public String showLoginForm(Model model) {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "auth/login";
    }

    @GetMapping("doctor-admin/login")
    public String showDoctorAdminLoginForm(Model model) {
        return "auth/loginadmin";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        SignupForm user = new SignupForm();
        model.addAttribute("user", user);
        return "auth/signup";
    }

    @GetMapping("/confirm-signup/{token}")
    public String confirmSignup(Model model, @PathVariable String token) {
        TokenChecker tokenChecker = authService.decodeToken(token);
        if (tokenChecker != null) {
            User user = tokenChecker.getUser();
            AuthenticationReponse response = authService.confirmSignup(user);

            if (response.getToken() != null) {
                model.addAttribute("registrationSuccess", true);
            }
        } else {
            model.addAttribute("tokenExpired", true);
        }

        return "auth/confirm-signup";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "auth/forgot-password";
    }

    @GetMapping("/reset-password/{token}")
    public String showResetPassword(Model model, @PathVariable("token") String token) {

        ResetPasswordForm form = new ResetPasswordForm();
        form.setToken(token);
        model.addAttribute("form", form);
        return "auth/reset-password";
    }

    @PostMapping("signup")
    public String signup(
            @ModelAttribute("user") @Valid SignupForm user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "auth/signup";
        }
        boolean success = authService.signup(user);
        if (success) {
            return "auth/check-mail";
        } else {
            model.addAttribute("message", "Some field is exist");
            return "auth/signup";
        }
    }

    @PostMapping("login")
    public String login(@ModelAttribute("user") @Valid LoginForm user,
            BindingResult result,
            Model model) {

        authService.login(user);

        return "auth/login";

    }

    @PostMapping("/forgot-password")
    public String commitForgotPassword(@ModelAttribute("email") String email) {
        authService.sendEmailForForgotPassword(email);
        return "auth/forgot-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(Model model, ResetPasswordForm form) {

        String message = authService.resetPassword(form);
        System.out.println(message);
        model.addAttribute("form", form);
        return "auth/reset-password";
    }

}
