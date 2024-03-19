package com.consult_app.demo.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.consult_app.demo.config.JwtService;
import com.consult_app.demo.dtos.AuthenticationReponse;
import com.consult_app.demo.dtos.InputSendEmailData;
import com.consult_app.demo.dtos.TokenChecker;
import com.consult_app.demo.forms.LoginForm;
import com.consult_app.demo.forms.ResetPasswordForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AuthService;
import com.consult_app.demo.services.CommonService;
import com.consult_app.demo.services.UserService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    CommonService commonService;

    @Autowired
    UserDetailsService userDetailsService;

    @Value("${frontend.url}")
    private String frontendPath;

    @Override
    public AuthenticationReponse login(LoginForm request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public boolean signup(SignupForm request) {
        try {
            User newUser = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .password(request.getPassword().trim())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .build();

            boolean success = userService.checkExistUser(newUser);
            if (success) {

                String token = jwtService.generateToken(newUser);
                String url = frontendPath + "/auth/confirm-signup/" + token;

                InputSendEmailData data = InputSendEmailData.builder()
                        .email(newUser.getEmail())
                        .url(url)
                        .template("mail/confirm-signup")
                        .build();
                commonService.sendEmail(data);

                return success;
            } else {
                return success;
            }
        } catch (Exception e) {
            // TODO: handle
        	System.err.println(e);
            return false;
        }
    }

    @Override
    public AuthenticationReponse confirmSignup(User user) {
        boolean userExist = userService.checkExistUser(user);
        if (!userExist) {
            return AuthenticationReponse.builder()
                    .message("This user is exist")
                    .token(null)
                    .build();
        }

        userService.createUser(user);
        User newUser = userService.getUserByEmail(user.getEmail());
        String token = jwtService.generateToken(newUser);

        return AuthenticationReponse.builder()
                .message("Sign up is successfully")
                .token(token)
                .build();
    }

    @Override
    public String sendEmailForForgotPassword(String email) {
        User user = userService.getUserByEmail(email);

        String token = jwtService.generateToken(user);
        String url = frontendPath + "/auth/reset-password/" + token;

        InputSendEmailData data = InputSendEmailData.builder()
                .email(user.getEmail())
                .url(url)
                .template("mail/forgot-password")
                .build();
        commonService.sendEmail(data);

        return "Send email is successfully";
    }

    @Override
    public String resetPassword(ResetPasswordForm form) {
        User user = jwtService.decodeJwt(form.getToken());
        if (!form.getConfirmNewPassword().matches(form.getNewPassword())) {
            return "Password don't match";
        }

        user.setPassword(form.getNewPassword());

        String message = userService.updateUser(user);

        return message;
    }

    @Override
    public TokenChecker decodeToken(String token) {
        User user = jwtService.decodeJwt(token);

        {

            return TokenChecker.builder()
                    .expired(false)
                    .user(user)
                    .build();
        }
    }

}
