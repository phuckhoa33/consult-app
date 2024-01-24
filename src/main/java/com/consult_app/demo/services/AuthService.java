package com.consult_app.demo.services;

import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;

public interface AuthService {
    AuthenticationReponse login(LoginForm request);

    String signup(SignupForm request);

    AuthenticationReponse confirmSignup(User user);

    String sendEmailForForgotPassword(String email);

    String resetPassword(User user, String newPassword);

}
