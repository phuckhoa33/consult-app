package com.consult_app.demo.services;

import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.dtos.TokenChecker;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.ResetPasswordForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;

public interface AuthService {
    AuthenticationReponse login(LoginForm request);

    boolean signup(SignupForm request);

    TokenChecker decodeToken(String token);

    AuthenticationReponse confirmSignup(User user);

    String sendEmailForForgotPassword(String email);

    String resetPassword(ResetPasswordForm form);

}
