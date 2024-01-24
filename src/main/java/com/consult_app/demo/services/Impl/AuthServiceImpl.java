package com.consult_app.demo.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.consult_app.demo.config.JwtService;
import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AuthService;
import com.consult_app.demo.services.UserService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationReponse login(LoginForm request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public String signup(SignupForm request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public AuthenticationReponse confirmSignup(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmSignup'");
    }

    @Override
    public String sendEmailForForgotPassword(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailForForgotPassword'");
    }

    @Override
    public String resetPassword(User user, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetPassword'");
    }

}
