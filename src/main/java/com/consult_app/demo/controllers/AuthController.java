package com.consult_app.demo.controllers;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.config.JwtService;
import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.dtos.TokenChecker;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.ResetPasswordForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AuthService;
import com.consult_app.demo.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
	   UserService userService;
    @Autowired
    JwtService jwtService;
    
    @GetMapping("/login")
    public String showLoginForm(Model model, @ModelAttribute("email") String email) {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        System.err.println(email);

        return "auth/login";
    }
    @PostMapping("/login")
    public String LoginForm(Model model, @ModelAttribute("email") String email
    		, @ModelAttribute("password") @Valid  String pass
    		) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	User usersaved=userService.getUserByEmail(email);
    	if(usersaved!=null) {
			Boolean password= passwordEncoder.matches(pass, usersaved.getPassword().trim());
			System.err.println(passwordEncoder.matches(pass, usersaved.getPassword()));
			System.err.println(usersaved.getPassword().length());
			System.err.println(pass);
			if(password) {
				if (usersaved.isBan()==true) {
					model.addAttribute("message","Tài khoản này đã bị khóa " );
					return "core/index";
				}
				 return "core/index";
			}else {
				model.addAttribute("message","Mật khẩu không đúng  " );
				return "auth/login";
			}
    	}else {
    		model.addAttribute("message","Tài khoản này chưa được đăng kí " );
    		  return "auth/login";
    	}
       
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
    public String forgotPassword(Model model) {
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

        boolean success = authService.signup(user);
        if (success) {
            return "auth/check-mail";
        } else {
            model.addAttribute("message", "Some field is exist");
            return "auth/signup";
        }
    }

//    @PostMapping("login")
//    public String login(@ModelAttribute("user") @Valid LoginForm user,
//            BindingResult result,
//            Model model) {
//        AuthenticationReponse reponse = authService.login(user);
//        return "auth/login";
//
//    }

    @PostMapping("/forgot-password")
    public String commitForgotPassword(@ModelAttribute("email") String email) {
        String result = authService.sendEmailForForgotPassword(email);
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
